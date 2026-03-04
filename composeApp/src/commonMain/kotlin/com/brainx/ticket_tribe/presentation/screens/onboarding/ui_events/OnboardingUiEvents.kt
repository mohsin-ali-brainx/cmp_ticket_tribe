package com.brainx.ticket_tribe.presentation.screens.onboarding.ui_events

sealed interface OnboardingUiEvents {
    sealed interface Navigate{
        data object MoveToSignUp : OnboardingUiEvents
        data object MoveToLogin : OnboardingUiEvents
    }
}