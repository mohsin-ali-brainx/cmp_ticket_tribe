package com.brainx.local_datastore.encryption

/**
 * Platform-agnostic interface for encrypting and decrypting data stored in DataStore.
 *
 * Implementations use platform-specific secure storage:
 * - **Android:** Android Keystore (hardware-backed when available)
 * - **iOS:** Keychain + CryptoKit
 *
 * Encryption uses AES-256-GCM with a random 12-byte IV per encryption.
 * Stored format: Base64(IV || ciphertext)
 */
internal interface DataStoreEncryption {

    /**
     * Encrypts plaintext data.
     *
     * @param identifier Unique key identifier for key lookup (e.g. "com.brainx.datastore.fileName.prefKey")
     * @param data Plaintext bytes to encrypt
     * @return Encrypted bytes: IV (12 bytes) || ciphertext
     */
    fun encrypt(identifier: String, data: ByteArray): ByteArray

    /**
     * Decrypts ciphertext data.
     *
     * @param identifier Key identifier used during encryption
     * @param data Encrypted bytes (IV || ciphertext)
     * @return Decrypted plaintext bytes
     */
    fun decrypt(identifier: String, data: ByteArray): ByteArray

    /**
     * Deletes the cryptographic key for the given identifier.
     * Call when removing an encrypted preference to clean up key storage.
     */
    fun deleteKey(identifier: String)
}
