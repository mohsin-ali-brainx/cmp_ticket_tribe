package com.brainx.domain.pref_manager

interface DatastorePrefManager {

    suspend fun setIsLogin(value:Boolean): Boolean
    suspend fun getIsLogin() : Boolean

    suspend fun setIsFirstTimeInstalled(value:Boolean): Boolean
    suspend fun getIsFirstTimeInstalled() : Boolean

    suspend fun getAccessToken(): String?
    suspend fun setAccessToken(value: String?): Boolean

    suspend fun getRefreshToken(): String?
    suspend fun setRefreshToken(value: String?): Boolean

    suspend fun clearSession()


}