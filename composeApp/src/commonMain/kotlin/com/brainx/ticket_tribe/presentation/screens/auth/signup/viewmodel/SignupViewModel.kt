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

    private fun isSignupFormButtonValid(state: SignupUiState): Boolean {

       return NameValidator().invoke(state.firstNameText).isValid
                && NameValidator().invoke(state.lastNameText, ignoreEmpty = true).isValid
                && EmailValidator().invoke(state.emailText).isValid
                && PasswordValidator().invoke(state.passwordText).isValid
                && ConfirmPasswordValidator().invoke(
            password = state.passwordText,
            confirmPassword = state.confirmPasswordText
        ).isValid

    }

    private fun isProfileFormButtonValid(state: SignupUiState): Boolean {

        return false

    }

    fun onIntent(intent: SignupUiIntents) {
        when (intent) {
            is SignupUiIntents.ButtonIntents.OnLoginButtonIntent -> {
                emitUIEvents(SignupUiEvents.Navigate.MoveToLogin)
            }
            is SignupUiIntents.ButtonIntents.OnNextButtonIntent -> {
                emitUIEvents(SignupUiEvents.Navigate.MoveToProfileSetup)
            }
            is SignupUiIntents.TextFieldsIntent.OnFirstNameTextUpdate -> {
                _state.update {
                    val updated = it.copy(firstNameText = intent.firstName)
                    updated.copy(isSignupFormButtonValid = isSignupFormButtonValid(updated))
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnLastNameTextUpdate -> {
                _state.update {
                    val updated = it.copy(lastNameText = intent.lastName)
                    updated.copy(isSignupFormButtonValid = isSignupFormButtonValid(updated))
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnEmailTextUpdate -> {
                _state.update {
                    val updated = it.copy(emailText = intent.email)
                    updated.copy(isSignupFormButtonValid = isSignupFormButtonValid(updated))
                }
            }

            is SignupUiIntents.TextFieldsIntent.OnPasswordTextUpdate -> {
                _state.update {
                    val updated = it.copy(passwordText = intent.password)
                    updated.copy(isSignupFormButtonValid = isSignupFormButtonValid(updated))
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnConfirmPasswordTextUpdate -> {
                _state.update {
                    val updated = it.copy(confirmPasswordText = intent.confirmPassword)
                    updated.copy(isSignupFormButtonValid = isSignupFormButtonValid(updated))
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnUserNameTextUpdate -> {
                _state.update {
                    val updated = it.copy(userNameText = intent.username)
                    updated.copy(isSignupProfileFormButtonValid = isProfileFormButtonValid(updated))
                }
            }
            is SignupUiIntents.TextFieldsIntent.OnLocationTextUpdate -> {
                _state.update {
                    val updated = it.copy(locationText = intent.location)
                    updated.copy(isSignupProfileFormButtonValid = isProfileFormButtonValid(updated))
                }
            }
            is SignupUiIntents.CheckBoxIntent.OnTermsCheckboxIntent->{
                _state.update {
                    val updated = it.copy(isTermsChecked = !it.isTermsChecked)
                    updated.copy(isSignupFormButtonValid = isProfileFormButtonValid(updated))
                }
            }
            else -> Unit
        }
    }

}