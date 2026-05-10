package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_events

import org.jetbrains.compose.resources.StringResource


sealed interface SignupUiEvents {
    sealed interface Navigate {
        data object MoveToLogin : SignupUiEvents
        data object MoveToProfileSetup : SignupUiEvents

    }

    sealed interface UIPrompts {
        data class ShowToastMessage(val message: String) : SignupUiEvents
        data class ShowToast(val message: StringResource) : SignupUiEvents
    }

}