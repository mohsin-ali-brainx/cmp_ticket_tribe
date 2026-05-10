package com.brainx.datasource.security

/**
 * Encrypts/decrypts small preference values (e.g. tokens) using platform hardware-backed key
 * material when available.
 *
 * Implementations must:
 * - be deterministic in terms of format (so values can be decrypted later)
 * - use authenticated encryption (AEAD)
 */
interface SecureValueCipher {
    fun encrypt(plainText: String): String
    fun decrypt(cipherText: String): String
}

/**
 * Platform implementation (Android: Keystore, iOS: Keychain + Crypto).
 */
expect class PlatformSecureValueCipher() : SecureValueCipher

internal object SecureValueCipherFormat {
    const val PREFIX = "enc:v1:"
    const val SEPARATOR = ":"
}

