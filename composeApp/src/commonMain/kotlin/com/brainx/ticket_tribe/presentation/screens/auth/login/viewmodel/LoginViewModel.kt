package com.brainx.ticket_tribe.presentation.screens.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.domain.use_cases.LoginUseCase
import com.brainx.domain.utils.resource_state.Resource
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_events.LoginUiEvents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_intents.LoginUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_state.LoginUiState
import com.brainx.ticket_tribe.utils.validators.EmailValidator
import com.brainx.utils_extensions.extensions.trimExtraSpaces
import com.brainx.utils_extensions.platformLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val datastore: DatastorePrefManager,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 500),
        initialValue = LoginUiState()
    )
    private val _eventFlow = Channel<LoginUiEvents>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: LoginUiEvents) {
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }

    private fun isFormButtonValid(
        email: String? = null,
        password: String? = null
    ): Boolean {
        _state.value.apply {
            return EmailValidator().invoke(email ?: emailText).isValid &&
                    (password ?: passwordText).isNotBlank()
        }
    }

    private fun onLogin() {
        if (isFormButtonValid()) {
            loginUseCase.invoke(
                email = state.value.emailText.trimExtraSpaces(),
                password = state.value.passwordText.trimExtraSpaces()
            ).onStart {
                if(state.value.isLoginLoading) return@onStart
                _state.update { it.copy(isLoginLoading = true) }
            }.onCompletion {
                if(!state.value.isLoginLoading) return@onCompletion
                _state.update { it.copy(isLoginLoading = false) }
            }.onEach { result ->
                when (result) {
                        is Resource.Success -> {
                            val responseResultData = result.data
                            val token = datastore.getAccessToken()
                            platformLog("LoginViewModel","success: $responseResultData")
                            platformLog("LoginViewModel", "token: $token")
                        }

                        is Resource.Error -> {
//                            _state.update { it.copy(isLoginLoading = false) }
                            print("LoginViewModel: error: ${result.message}")

                        }

                        is Resource.Loading -> {
                            if(state.value.isLoginLoading==result.isLoading) return@onEach
                            _state.update { it.copy(isLoginLoading = result.isLoading) }
                        }
                    }
                }
                .flowOn(ioDispatcher)
                .launchIn(viewModelScope)
        }
    }

    fun onIntent(intent: LoginUiIntents) {
        when (intent) {
            is LoginUiIntents.ButtonIntents.OnSignUpButtonIntent -> {
                emitUIEvents(LoginUiEvents.Navigate.MoveToSignUp)
            }

            is LoginUiIntents.TextFieldsIntent.OnEmailTextUpdate -> {
                _state.update {
                    it.copy(
                        emailText = intent.email,
                        isFormButtonValid = isFormButtonValid(email = intent.email)
                    )
                }
            }

            is LoginUiIntents.TextFieldsIntent.OnPasswordTextUpdate -> {
                _state.update {
                    it.copy(
                        passwordText = intent.password,
                        isFormButtonValid = isFormButtonValid(password = intent.password)
                    )
                }
            }

            is LoginUiIntents.ButtonIntents.OnLoginButtonIntent -> {
                onLogin()
            }

            is LoginUiIntents.ButtonIntents.OnForgotButtonIntent -> {

            }
        }
    }

}