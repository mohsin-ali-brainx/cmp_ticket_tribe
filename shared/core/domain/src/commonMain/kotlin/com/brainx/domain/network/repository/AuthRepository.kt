package com.brainx.domain.network.repository

import com.brainx.domain.network.dto.LoginDto
import com.brainx.domain.network.models.response_models.auth.LoginResponseModel
import com.brainx.domain.network.result_state.NetworkResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(authRequest: LoginDto): Flow<NetworkResultState<LoginResponseModel>>

}