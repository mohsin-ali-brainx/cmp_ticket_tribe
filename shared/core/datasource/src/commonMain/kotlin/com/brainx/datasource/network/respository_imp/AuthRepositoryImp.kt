package com.brainx.datasource.network.respository_imp

import com.brainx.datasource.network.ApiEndpoints
import com.brainx.datasource.network.mappers.toNetworkResultState
import com.brainx.datasource.network.models.mappers.auth.toMapper
import com.brainx.datasource.network.models.response_models.auth.LoginResponse
import com.brainx.datasource.network.models.response_models.base.BaseResponse
import com.brainx.domain.network.dto.LoginDto
import com.brainx.domain.network.models.response_models.auth.LoginResponseModel
import com.brainx.domain.network.repository.AuthRepository
import com.brainx.domain.network.result_state.NetworkResultState
import com.brainx.ktor_network.core.safe_call.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class AuthRepositoryImp(
    private val httpClient: HttpClient,
) : AuthRepository {
    override fun login(
        authRequest: LoginDto
    ): Flow<NetworkResultState<LoginResponseModel>> = channelFlow {
        val result = safeNetworkCall<BaseResponse<LoginResponse>>(
            block = httpClient.post(urlString = ApiEndpoints.Auth.LOGIN) {
                setBody(authRequest)
            }
        )

        send(
            result.toNetworkResultState { response ->
                response?.data?.toMapper()
            }
        )

    }
}