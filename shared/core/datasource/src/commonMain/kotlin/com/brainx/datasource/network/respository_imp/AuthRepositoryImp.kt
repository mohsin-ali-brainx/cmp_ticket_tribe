package com.brainx.datasource.network.respository_imp

import com.brainx.datasource.local_database.mappers.toUserEntity
import com.brainx.datasource.network.ApiEndpoints
import com.brainx.datasource.network.mappers.toNetworkResultState
import com.brainx.datasource.network.models.mappers.auth.toLoginModel
import com.brainx.datasource.network.models.response_models.auth.LoginResponse
import com.brainx.datasource.network.models.response_models.base.BaseResponse
import com.brainx.datasource.network.models.dto.LoginDto
import com.brainx.domain.network.models.response_models.auth.LoginModel
import com.brainx.domain.network.repository.AuthRepository
import com.brainx.domain.network.result_state.NetworkResultState
import com.brainx.ktor_network.core.safe_call.safeNetworkCall
import com.brainx.room_database.database.dao.UserDao
import com.brainx.room_database.repository.UserDbRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class AuthRepositoryImp(
    private val httpClient: HttpClient,
) : AuthRepository {
    override fun login(
        email:String,password:String
    ): Flow<NetworkResultState<LoginModel>> = channelFlow {
        val result = safeNetworkCall<BaseResponse<LoginResponse>>(
            block = httpClient.post(urlString = ApiEndpoints.Auth.LOGIN) {
                setBody(LoginDto(email = email, password = password))
            }
        )

        send(
            result.toNetworkResultState { response ->
                response?.data?.toLoginModel()
            }
        )

    }
}