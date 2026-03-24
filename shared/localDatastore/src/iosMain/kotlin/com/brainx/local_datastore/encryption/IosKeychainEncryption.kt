package com.brainx.local_datastore.encryption

import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.providers.cryptokit.CryptoKit
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.value
import kotlinx.coroutines.runBlocking
import platform.CoreFoundation.CFDictionaryCreateMutable
import platform.CoreFoundation.CFDictionarySetValue
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFTypeRef
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreFoundation.kCFBooleanTrue
import platform.Foundation.CFBridgingRelease
import platform.Foundation.CFBridgingRetain
import platform.Foundation.NSData
import platform.Foundation.dataWithBytes
import platform.Security.SecItemAdd
import platform.Security.SecItemCopyMatching
import platform.Security.SecItemDelete
import platform.Security.errSecInteractionNotAllowed
import platform.Security.errSecSuccess
import platform.Security.kSecAttrAccessible
import platform.Security.kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly
import platform.Security.kSecAttrAccount
import platform.Security.kSecAttrService
import platform.Security.kSecClass
import platform.Security.kSecClassGenericPassword
import platform.Security.kSecReturnData
import platform.Security.kSecMatchLimit
import platform.Security.kSecMatchLimitOne
import platform.Security.kSecValueData
import platform.posix.memcpy
import kotlin.random.Random

/**
 * iOS implementation of [DataStoreEncryption] using iOS Keychain and CryptoKit.
 *
 * AES-256 keys are stored as Keychain generic-password items, protected by device passcode.
 * Keys use kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly (not backed up to iCloud).
 */
internal class IosKeychainEncryption(
    private val serviceName: String = "com.brainx.local_datastore"
) : DataStoreEncryption {

    companion object {
        private const val KEY_SIZE_BYTES = 32
    }

    override fun encrypt(identifier: String, data: ByteArray): ByteArray {
        val keyBytes = getOrCreateKeychainKey(identifier)
        return runBlocking {
            val aesGcm = CryptographyProvider.CryptoKit.get(AES.GCM)
            val symmetricKey = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)
            val cipher = symmetricKey.cipher()
            cipher.encrypt(plaintext = data)
        }
    }

    override fun decrypt(identifier: String, data: ByteArray): ByteArray {
        val keyBytes = getExistingKeychainKey(identifier)
        return runBlocking {
            val aesGcm = CryptographyProvider.CryptoKit.get(AES.GCM)
            val symmetricKey = aesGcm.keyDecoder().decodeFromByteArray(AES.Key.Format.RAW, keyBytes)
            val cipher = symmetricKey.cipher()
            cipher.decrypt(ciphertext = data)
        }
    }

    override fun deleteKey(identifier: String) {
        deleteFromKeychain(identifier)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getExistingKeychainKey(keyId: String): ByteArray {
        return memScoped {
            val query = CFDictionaryCreateMutable(
                kCFAllocatorDefault, 0, null, null
            ).apply {
                CFDictionarySetValue(this, kSecClass, kSecClassGenericPassword)
                CFDictionarySetValue(this, kSecAttrService, CFBridgingRetain(serviceName))
                CFDictionarySetValue(this, kSecAttrAccount, CFBridgingRetain(keyId))
                CFDictionarySetValue(this, kSecReturnData, kCFBooleanTrue)
                CFDictionarySetValue(this, kSecMatchLimit, kSecMatchLimitOne)
            }

            val resultRef = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(query, resultRef.ptr)
            CFRelease(query as CFTypeRef?)

            when (status) {
                errSecSuccess -> {
                    val data = CFBridgingRelease(resultRef.value) as NSData
                    data.toByteArray()
                }
                platform.Security.errSecItemNotFound ->
                    throw IllegalStateException("EncryptedDataStore: No encryption key found for: $keyId")
                errSecInteractionNotAllowed ->
                    throw IllegalStateException("EncryptedDataStore: Cannot access Keychain - device is locked.")
                else ->
                    throw IllegalStateException("EncryptedDataStore: Keychain error $status for key $keyId")
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getOrCreateKeychainKey(keyId: String): ByteArray {
        val existingKey = memScoped {
            val query = CFDictionaryCreateMutable(
                kCFAllocatorDefault, 0, null, null
            ).apply {
                CFDictionarySetValue(this, kSecClass, kSecClassGenericPassword)
                CFDictionarySetValue(this, kSecAttrService, CFBridgingRetain(serviceName))
                CFDictionarySetValue(this, kSecAttrAccount, CFBridgingRetain(keyId))
                CFDictionarySetValue(this, kSecReturnData, kCFBooleanTrue)
                CFDictionarySetValue(this, kSecMatchLimit, kSecMatchLimitOne)
            }

            val resultRef = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(query, resultRef.ptr)
            CFRelease(query as CFTypeRef?)

            when (status) {
                errSecSuccess -> {
                    val data = CFBridgingRelease(resultRef.value) as NSData
                    return@memScoped data.toByteArray()
                }
                platform.Security.errSecItemNotFound -> return@memScoped null
                errSecInteractionNotAllowed ->
                    throw IllegalStateException("EncryptedDataStore: Cannot access Keychain - device is locked.")
                else ->
                    throw IllegalStateException("EncryptedDataStore: Keychain error $status for key $keyId")
            }
        }

        if (existingKey != null) return existingKey

        val newKey = ByteArray(KEY_SIZE_BYTES)
        Random.nextBytes(newKey)
        storeInKeychain(keyId, newKey)
        return newKey
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun storeInKeychain(keyId: String, keyData: ByteArray) {
        val nsData = keyData.usePinned { pinned ->
            NSData.dataWithBytes(
                bytes = pinned.addressOf(0),
                length = keyData.size.toULong()
            )
        }
        memScoped {

            val addQuery = CFDictionaryCreateMutable(
                kCFAllocatorDefault, 0, null, null
            ).apply {
                CFDictionarySetValue(this, kSecClass, kSecClassGenericPassword)
                CFDictionarySetValue(this, kSecAttrService, CFBridgingRetain(serviceName))
                CFDictionarySetValue(this, kSecAttrAccount, CFBridgingRetain(keyId))
                CFDictionarySetValue(this, kSecValueData, CFBridgingRetain(nsData))
                CFDictionarySetValue(this, kSecAttrAccessible, kSecAttrAccessibleAfterFirstUnlockThisDeviceOnly)
            }

            val deleteQuery = CFDictionaryCreateMutable(
                kCFAllocatorDefault, 0, null, null
            ).apply {
                CFDictionarySetValue(this, kSecClass, kSecClassGenericPassword)
                CFDictionarySetValue(this, kSecAttrService, CFBridgingRetain(serviceName))
                CFDictionarySetValue(this, kSecAttrAccount, CFBridgingRetain(keyId))
            }
            SecItemDelete(deleteQuery)
            CFRelease(deleteQuery as CFTypeRef?)

            val addStatus = SecItemAdd(addQuery, null)
            CFRelease(addQuery as CFTypeRef?)

            if (addStatus != errSecSuccess) {
                when (addStatus) {
                    errSecInteractionNotAllowed ->
                        throw IllegalStateException("EncryptedDataStore: Cannot store key - device is locked.")
                    else ->
                        throw IllegalStateException("EncryptedDataStore: Failed to store key in Keychain: $addStatus")
                }
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun deleteFromKeychain(keyId: String) {
        memScoped {
            val query = CFDictionaryCreateMutable(
                kCFAllocatorDefault, 0, null, null
            ).apply {
                CFDictionarySetValue(this, kSecClass, kSecClassGenericPassword)
                CFDictionarySetValue(this, kSecAttrService, CFBridgingRetain(serviceName))
                CFDictionarySetValue(this, kSecAttrAccount, CFBridgingRetain(keyId))
            }
            SecItemDelete(query)
            CFRelease(query as CFTypeRef?)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun NSData.toByteArray(): ByteArray {
        return ByteArray(this.length.toInt()).apply {
            usePinned {
                memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
            }
        }
    }
}

internal actual fun createDataStoreEncryption(
    keyPrefix: String,
    datastoreName: String
): DataStoreEncryption = IosKeychainEncryption()
