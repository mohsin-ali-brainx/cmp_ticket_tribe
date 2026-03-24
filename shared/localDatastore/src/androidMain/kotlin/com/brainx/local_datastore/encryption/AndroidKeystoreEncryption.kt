package com.brainx.local_datastore.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Android implementation of [DataStoreEncryption] using the Android Keystore System.
 *
 * Provides hardware-backed encryption with keys that are:
 * - Non-exportable
 * - Bound to the application
 * - Automatically deleted on app uninstall
 */
internal class AndroidKeystoreEncryption : DataStoreEncryption {

    companion object {
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val GCM_TAG_LENGTH = 128
        private const val GCM_IV_LENGTH = 12
        private const val KEY_SIZE = 256
    }

    private val keyCache = java.util.concurrent.ConcurrentHashMap<String, SecretKey>()
    private val locks = java.util.concurrent.ConcurrentHashMap<String, Any>()
    private fun lockFor(alias: String): Any = locks.computeIfAbsent(alias) { Any() }

    override fun encrypt(identifier: String, data: ByteArray): ByteArray {
        return try {
            encryptWithKey(identifier, data)
        } catch (e: KeyPermanentlyInvalidatedException) {
            deleteKeyInternal(identifier)
            encryptWithKey(identifier, data)
        }
    }

    private fun encryptWithKey(identifier: String, data: ByteArray): ByteArray {
        val secretKey = getOrCreateSecretKey(identifier)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        } catch (e: java.security.InvalidKeyException) {
            throw IllegalStateException("EncryptedDataStore: Cannot access Keystore - device may be locked.", e)
        }
        val iv = cipher.iv
        val ciphertext = cipher.doFinal(data)
        return iv + ciphertext
    }

    override fun decrypt(identifier: String, data: ByteArray): ByteArray {
        return try {
            decryptWithKey(identifier, data)
        } catch (e: KeyPermanentlyInvalidatedException) {
            deleteKeyInternal(identifier)
            throw e
        }
    }

    private fun decryptWithKey(identifier: String, data: ByteArray): ByteArray {
        val secretKey = getExistingSecretKey(identifier)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val iv = data.sliceArray(0 until GCM_IV_LENGTH)
        val ciphertext = data.sliceArray(GCM_IV_LENGTH until data.size)
        val spec = GCMParameterSpec(GCM_TAG_LENGTH, iv)
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        } catch (e: java.security.InvalidKeyException) {
            throw IllegalStateException("EncryptedDataStore: Cannot access Keystore - device may be locked.", e)
        }
        return cipher.doFinal(ciphertext)
    }

    override fun deleteKey(identifier: String) {
        deleteKeyInternal(identifier)
    }

    private fun deleteKeyInternal(identifier: String) {
        synchronized(lockFor(identifier)) {
            keyCache.remove(identifier)
            try {
                val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
                if (keyStore.containsAlias(identifier)) {
                    keyStore.deleteEntry(identifier)
                }
            } catch (_: Exception) { /* key may not exist */ }
        }
    }

    private fun getExistingSecretKey(identifier: String): SecretKey {
        keyCache[identifier]?.let { return it }
        synchronized(lockFor(identifier)) {
            keyCache[identifier]?.let { return it }
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
            if (!keyStore.containsAlias(identifier)) {
                throw IllegalStateException("EncryptedDataStore: No encryption key found for: $identifier")
            }
            val key = keyStore.getKey(identifier, null) as SecretKey
            keyCache[identifier] = key
            return key
        }
    }

    private fun getOrCreateSecretKey(identifier: String): SecretKey {
        keyCache[identifier]?.let { return it }
        synchronized(lockFor(identifier)) {
            keyCache[identifier]?.let { return it }
            val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
            val key = if (keyStore.containsAlias(identifier)) {
                keyStore.getKey(identifier, null) as SecretKey
            } else {
                val keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    ANDROID_KEYSTORE
                )
                val spec = KeyGenParameterSpec.Builder(
                    identifier,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(KEY_SIZE)
                    .build()
                keyGenerator.init(spec)
                keyGenerator.generateKey()
            }
            keyCache[identifier] = key
            return key
        }
    }
}

internal actual fun createDataStoreEncryption(
    keyPrefix: String,
    datastoreName: String
): DataStoreEncryption = AndroidKeystoreEncryption()
