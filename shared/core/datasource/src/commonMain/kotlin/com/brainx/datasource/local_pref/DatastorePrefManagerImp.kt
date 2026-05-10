package com.brainx.datasource.local_pref

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.local_datastore.encryption.EncryptedDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class DatastorePrefManagerImp(
    private val encryptedDataStore: EncryptedDataStore
) : DatastorePrefManager {

    private enum class SPKeys(val key: String) {
        IS_LOGIN("is_login"),
        IS_FIRST_TIME_INSTALLED("is_first_time_installed"),
        ACCESS_TOKEN("access_token"),
        REFRESH_TOKEN("refresh_token")
    }

    private companion object {
        private val IS_LOGIN_PREF_KEY = booleanPreferencesKey(SPKeys.IS_LOGIN.key)
        private val IS_FIRST_TIME_INSTALLED_PREF_KEY = booleanPreferencesKey(SPKeys.IS_FIRST_TIME_INSTALLED.key)
    }

    private val dataStore = encryptedDataStore.innerDataStore

    override suspend fun setIsLogin(value: Boolean): Boolean {
        return try {
            dataStore.edit { preferences: MutablePreferences ->
                preferences[IS_LOGIN_PREF_KEY] = value
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getIsLogin(): Boolean =
        dataStore.data.map { preferences ->
            preferences[IS_LOGIN_PREF_KEY]
        }.first() ?: false

    override suspend fun setIsFirstTimeInstalled(value: Boolean): Boolean {
        return try {
            dataStore.edit { preferences: MutablePreferences ->
                preferences[IS_FIRST_TIME_INSTALLED_PREF_KEY] = value
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getIsFirstTimeInstalled(): Boolean =
        dataStore.data.map { preferences ->
            preferences[IS_FIRST_TIME_INSTALLED_PREF_KEY]
        }.first() ?: false

    override suspend fun getAccessToken(): String? =
        encryptedDataStore.get(SPKeys.ACCESS_TOKEN.key, null as String?)

    override suspend fun setAccessToken(value: String?): Boolean {
        return try {
            if (value == null || value.isBlank()) {
                encryptedDataStore.delete(SPKeys.ACCESS_TOKEN.key)
            } else {
                encryptedDataStore.put(SPKeys.ACCESS_TOKEN.key, value)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getRefreshToken(): String? =
        encryptedDataStore.get(SPKeys.REFRESH_TOKEN.key, null as String?)

    override suspend fun setRefreshToken(value: String?): Boolean {
        return try {
            if (value == null || value.isBlank()) {
                encryptedDataStore.delete(SPKeys.REFRESH_TOKEN.key)
            } else {
                encryptedDataStore.put(SPKeys.REFRESH_TOKEN.key, value)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun clearSession() {
        dataStore.edit { preferences: MutablePreferences ->
            preferences.remove(IS_LOGIN_PREF_KEY)
        }
        encryptedDataStore.delete(SPKeys.ACCESS_TOKEN.key)
        encryptedDataStore.delete(SPKeys.REFRESH_TOKEN.key)
    }
}
