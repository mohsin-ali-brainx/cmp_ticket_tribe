package com.brainx.ticket_tribe.presentation.screens.auth.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.domain.db.repository.LocalDbUserRepository
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_events.SignupUiEvents
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_intents.SignupUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_state.SignupUiState
import com.brainx.ticket_tribe.utils.validators.ConfirmPasswordValidator
import com.brainx.ticket_tribe.utils.validators.EmailValidator
import com.brainx.ticket_tribe.utils.validators.NameValidator
import com.brainx.ticket_tribe.utils.validators.PasswordValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel (
    private val ioDispatcher: CoroutineDispatcher,
    private val datastore: DatastorePrefManager,
    private val userDbUserRepository: LocalDbUserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignupUiState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 500),
        initialValue = SignupUiState()
    )
    private val _eventFlow = Channel<SignupUiEvents>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: SignupUiEvents) {
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }

    private fun isFormButtonValid(
        email: String? = null,
        password: String? = null,
        firstName:String?=null,
        lastName:String?=null,
        confirmPassword:String?=null
    ): Boolean {
        _state.value.apply {
            return EmailValidator().invoke(email ?: emailText).isValid
                    && NameValidator().invoke(text = firstName?:firstNameText,).isValid
                    && PasswordValidator().invoke(password = password?:passwordText).isValid
                    && ConfirmPasswordValidator().invoke(confirmPassword = confirmPassword?:confirmPasswordText, password = password?:passwordText).isValid
        }
    }

    fun onIntent(intent: SignupUiIntents) {
        when (intent) {
            is SignupUiIntents.ButtonIntents.OnLoginButtonIntent -> {
                emitUIEvents(SignupUiEvents.Navigate.MoveToLogin)
            }
            is SignupUiIntents.TextFieldsIntent.OnFirstNameTextUpdate -> {
                _state.update {
                    it.copy(
                        firstNameText = intent.firstName,
                        isFormButtonValid = isFormButtonValid(firstName = intent.firstName)
                    )
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnLastNameTextUpdate -> {
                _state.update {
                    it.copy(
                        lastNameText = intent.lastName,
                        isFormButtonValid = isFormButtonValid(lastName = intent.lastName)
                    )
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnEmailTextUpdate -> {
                _state.update {
                    it.copy(
                        emailText = intent.email,
                        isFormButtonValid = isFormButtonValid(email = intent.email)
                    )
                }
            }

            is SignupUiIntents.TextFieldsIntent.OnPasswordTextUpdate -> {
                _state.update {
                    it.copy(
                        passwordText = intent.password,
                        isFormButtonValid = isFormButtonValid(password = intent.password)
                    )
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnConfirmPasswordTextUpdate -> {
                _state.update {
                    it.copy(
                        confirmPasswordText = intent.confirmPassword,
                        isFormButtonValid = isFormButtonValid(confirmPassword = intent.confirmPassword)
                    )
                }
            }
            else -> Unit
        }
    }

}