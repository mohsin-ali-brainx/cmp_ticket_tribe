package com.brainx.ticket_tribe.presentation.screens.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_events.LoginUiEvents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_intents.LoginUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_state.LoginUiState
import com.brainx.ticket_tribe.utils.validators.EmailValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel (
    private val ioDispatcher : CoroutineDispatcher,
    private val localDataStore: DatastorePrefManager,
):ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 500) ,
        initialValue = LoginUiState()
    )
    private val _eventFlow = Channel<LoginUiEvents>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: LoginUiEvents){
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }

    private fun isFormButtonValid(
        email:String?=null,
        password:String?=null
    ):Boolean{
        _state.value.apply {
            return EmailValidator().invoke(email?:emailText).isValid &&
                    (password?:passwordText).isNotBlank()
        }
    }
    fun onIntent(intent: LoginUiIntents){
        when(intent){
            is LoginUiIntents.ButtonIntents.OnSignUpButtonIntent->{
                emitUIEvents(LoginUiEvents.Navigate.MoveToSignUp)
            }
            is LoginUiIntents.TextFieldsIntent.OnEmailTextUpdate->{
                _state.update { it.copy(emailText = intent.email, isFormButtonValid = isFormButtonValid(email = intent.email)) }
            }
            is LoginUiIntents.TextFieldsIntent.OnPasswordTextUpdate->{
                _state.update { it.copy(passwordText = intent.password,isFormButtonValid = isFormButtonValid(password = intent.password)) }
            }
            is LoginUiIntents.ButtonIntents.OnLoginButtonIntent-> {

            }
        }
    }
}