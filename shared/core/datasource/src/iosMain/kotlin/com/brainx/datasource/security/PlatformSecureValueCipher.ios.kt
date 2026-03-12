package com.brainx.datasource.security

/**
 * iOS: No-op implementation. Tokens are stored in DataStore without encryption.
 * Only Android uses encryption (Keystore).
 */
actual class PlatformSecureValueCipher actual constructor() : SecureValueCipher {

    override fun encrypt(plainText: String): String = plainText

    override fun decrypt(cipherText: String): String = cipherText
}
