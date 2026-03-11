package com.brainx.ticket_tribe.presentation.screens.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.ticket_tribe.presentation.screens.onboarding.ui_events.OnboardingUiEvents
import com.brainx.ticket_tribe.presentation.screens.onboarding.ui_intents.OnboardingUiIntents
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingViewModel(
    private val ioDispatcher : CoroutineDispatcher,
    private val localDataStore: DatastorePrefManager
) : ViewModel() {
    private val _eventFlow = Channel<OnboardingUiEvents>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: OnboardingUiEvents){
        viewModelScope.launch {
            withContext(ioDispatcher){
                localDataStore.setIsFirstTimeInstalled(true)
            }
            _eventFlow.send(event)
        }
    }

    fun onIntent(intent: OnboardingUiIntents){
        when(intent){
            is OnboardingUiIntents.ButtonIntents.OnSignUpButtonIntent->{
                emitUIEvents(OnboardingUiEvents.Navigate.MoveToSignUp)
            }
            is OnboardingUiIntents.ButtonIntents.OnLogInButtonIntent->{
                emitUIEvents(OnboardingUiEvents.Navigate.MoveToLogin)
            }
        }
    }
}