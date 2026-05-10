package com.brainx.domain.network.repository


import com.brainx.domain.network.models.response_models.auth.LoginModel
import com.brainx.domain.network.result_state.NetworkResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email:String,password:String): Flow<NetworkResultState<LoginModel>>

}