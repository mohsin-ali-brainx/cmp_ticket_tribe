package com.brainx.datasource.network.managers

import com.brainx.datasource.network.ApiEndpoints
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.ktor_network.core.interfaces.TokenProvider
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class NetworkTokenProviderManager(
    val datastorePreferenceManager: DatastorePrefManager
) : TokenProvider {
    override suspend fun getAccessTokenEndpoint(): String? = ApiEndpoints.Auth.REFRESH_TOKEN


    override suspend fun getAccessToken(): String? = datastorePreferenceManager.getAccessToken()


    override suspend fun getRefreshToken(): String? = datastorePreferenceManager.getRefreshToken()

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        updateAccessAndRefreshTokens(accessToken,refreshToken)
    }

    override suspend fun clear() {
        updateAccessAndRefreshTokens(ExtConstants.StringConstants.EMPTY,ExtConstants.StringConstants.EMPTY)
    }

    private suspend fun updateAccessAndRefreshTokens(accessToken: String, refreshToken: String){
        Mutex().withLock {
            datastorePreferenceManager.apply {
                setAccessToken(accessToken)
                setRefreshToken(refreshToken)
            }
        }
    }
}