package com.brainx.ticket_tribe.presentation.screens.auth.login.ui_events

sealed interface LoginUiEvents {
    sealed interface Navigate {
        data object MoveToSignUp : LoginUiEvents
    }

}