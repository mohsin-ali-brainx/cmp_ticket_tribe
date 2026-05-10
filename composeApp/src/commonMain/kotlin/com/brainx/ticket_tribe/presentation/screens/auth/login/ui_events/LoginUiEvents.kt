package com.brainx.ticket_tribe.presentation.screens.auth.login.ui_events

import org.jetbrains.compose.resources.StringResource

sealed interface LoginUiEvents {
    sealed interface Navigate {
        data object MoveToSignUp : LoginUiEvents
    }

    sealed interface UIPrompts {
        data class ShowToastMessage(val message: String) : LoginUiEvents
        data class ShowToast(val message: StringResource) : LoginUiEvents

    }

}