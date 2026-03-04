package com.brainx.ticket_tribe.presentation.screens.onboarding.ui_intents

sealed interface OnboardingUiIntents {
    sealed interface ButtonIntents{
        data object OnSignUpButtonIntent : OnboardingUiIntents
        data object OnLogInButtonIntent : OnboardingUiIntents
    }
}