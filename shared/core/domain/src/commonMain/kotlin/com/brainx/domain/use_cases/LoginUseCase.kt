package com.brainx.domain.use_cases

import com.brainx.domain.network.models.response_models.user.UserModel
import com.brainx.domain.network.repository.AuthRepository
import com.brainx.domain.network.result_state.NetworkResultState
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.domain.utils.resource_state.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class LoginUseCase(
    val authRepository: AuthRepository,
    val datastore: DatastorePrefManager
) {
    operator fun invoke(email: String,password: String) : Flow<Resource<UserModel>>{
        val response = authRepository.login(email = email, password = password)
        return response.map { resultState ->
            when(resultState){
                is NetworkResultState.Success->{
                    val data = resultState.data
                    setLoginSettings(access =data?.access, refresh =  data?.refresh, isLogin = true)
                    Resource.Success(data?.user)
                }
                is NetworkResultState.Error -> {
                    setLoginSettings(access =null, refresh =  null, isLogin = false)
                    Resource.Error(resultState.error.errorMsg , null)
                }
                is NetworkResultState.SuccessWithErrorData->{
                    setLoginSettings(access =null, refresh =  null, isLogin = false)
                    Resource.Error(resultState.error.errorMsg, null)
                }
            }
        }.onStart {
            emit(Resource.Loading(true))
        }.catch {
            emit(Resource.Error(message = it.message ?: "Unknown Error"))
        }.onCompletion {
            emit(Resource.Loading(false))
        }
    }

    private suspend fun setLoginSettings(access: String?, refresh: String?, isLogin: Boolean){
        datastore.apply {
            setAccessToken(access)
            setRefreshToken(refresh)
            setIsLogin(isLogin)
        }
    }
}