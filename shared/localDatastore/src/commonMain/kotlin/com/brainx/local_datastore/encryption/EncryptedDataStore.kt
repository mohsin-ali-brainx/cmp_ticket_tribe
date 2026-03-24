package com.brainx.local_datastore.encryption

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Encrypted wrapper around [DataStore] of [Preferences].
 *
 * All values are encrypted with AES-256-GCM before being stored.
 * Keys are stored in platform-specific secure storage (Android Keystore, iOS Keychain).
 *
 * @param dataStore The underlying DataStore
 * @param encryption Platform-specific encryption engine
 * @param keyPrefix Prefix for key identifiers (e.g. "com.brainx.local_datastore")
 * @param datastoreName Name of the DataStore (used in key aliases)
 * @param json Json instance for serialization (optional)
 */
class EncryptedDataStore internal constructor(
    private val dataStore: DataStore<Preferences>,
    private val encryption: DataStoreEncryption,
    private val keyPrefix: String,
    private val datastoreName: String,
    private val json: Json = EncryptedDataStoreDefaults.json
) {

    private companion object {
        private const val VALUE_PREFIX = "__enc_value_"
        private const val META_PREFIX = "__enc_meta_"
        private const val META_SUFFIX = "__"
    }

    private fun keyIdentifier(preferenceKey: String): String =
        listOf(keyPrefix, datastoreName, preferenceKey).joinToString(".")

    private fun valuePrefKey(key: String) = stringPreferencesKey("$VALUE_PREFIX$key")
    private fun metaPrefKey(key: String) = stringPreferencesKey("$META_PREFIX$key$META_SUFFIX")

    @OptIn(ExperimentalEncodingApi::class)
    private fun encodeBase64(bytes: ByteArray): String = Base64.encode(bytes)

    @OptIn(ExperimentalEncodingApi::class)
    private fun decodeBase64(encoded: String): ByteArray = Base64.decode(encoded)

    /**
     * Retrieves a decrypted value.
     *
     * @param key Preference key
     * @param defaultValue Value to return if key doesn't exist or decryption fails
     * @param serializer KSerializer for type T
     * @return Stored value or defaultValue
     */
    suspend fun <T> get(
        key: String,
        defaultValue: T,
        serializer: KSerializer<T>
    ): T {
        return try {
            val prefs = dataStore.data.first()
            val enc = prefs[valuePrefKey(key)] ?: return defaultValue

            val ciphertext = decodeBase64(enc)
            val keyId = keyIdentifier(key)
            val plaintext = encryption.decrypt(keyId, ciphertext)
            val jsonString = plaintext.decodeToString()

            json.decodeFromString(serializer, jsonString)
        } catch (e: Exception) {
            defaultValue
        }
    }

    /**
     * Retrieves a decrypted value (reified version).
     */
    inline suspend fun <reified T> get(key: String, defaultValue: T): T =
        get(key, defaultValue, serializer())

    /**
     * Stores an encrypted value.
     *
     * @param key Preference key
     * @param value Value to store (will be serialized to JSON and encrypted)
     * @param serializer KSerializer for type T
     */
    suspend fun <T> put(
        key: String,
        value: T,
        serializer: KSerializer<T>
    ) {
        val keyId = keyIdentifier(key)
        val jsonString = json.encodeToString(serializer, value)
        val ciphertext = encryption.encrypt(keyId, jsonString.encodeToByteArray())

        dataStore.edit { prefs ->
            prefs[valuePrefKey(key)] = encodeBase64(ciphertext)
            prefs[metaPrefKey(key)] = "1"
        }
    }

    /**
     * Stores an encrypted value (reified version).
     */
    inline suspend fun <reified T> put(key: String, value: T) {
        put(key, value, serializer())
    }

    /**
     * Observes an encrypted value as a Flow.
     */
    fun <T> getFlow(
        key: String,
        defaultValue: T,
        serializer: KSerializer<T>
    ): Flow<T> = dataStore.data.map { prefs ->
        val enc = prefs[valuePrefKey(key)] ?: return@map defaultValue
        try {
            val ciphertext = decodeBase64(enc)
            val keyId = keyIdentifier(key)
            val plaintext = encryption.decrypt(keyId, ciphertext)
            val jsonString = plaintext.decodeToString()
            json.decodeFromString(serializer, jsonString)
        } catch (e: Exception) {
            defaultValue
        }
    }.distinctUntilChanged()

    /**
     * Observes an encrypted value as a Flow (reified version).
     */
    inline fun <reified T> getFlow(key: String, defaultValue: T): Flow<T> =
        getFlow(key, defaultValue, serializer())

    /**
     * Deletes an encrypted value and its encryption key.
     */
    suspend fun delete(key: String) {
        dataStore.edit { prefs ->
            prefs.remove(valuePrefKey(key))
            prefs.remove(metaPrefKey(key))
        }
        encryption.deleteKey(keyIdentifier(key))
    }

    /**
     * The underlying DataStore for direct access when encryption is not needed.
     */
    val innerDataStore: DataStore<Preferences> = dataStore
}

/**
 * Default Json configuration for EncryptedDataStore.
 */
object EncryptedDataStoreDefaults {
    val json: Json = Json {
        ignoreUnknownKeys = true
    }
}
