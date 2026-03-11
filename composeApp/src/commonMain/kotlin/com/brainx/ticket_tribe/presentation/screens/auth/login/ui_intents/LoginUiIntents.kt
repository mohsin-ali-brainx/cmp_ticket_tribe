package com.brainx.ticket_tribe.presentation.screens.auth.login.ui_intents

import kotlin.jvm.JvmInline

sealed interface LoginUiIntents {
    sealed interface ButtonIntents{
        data object OnSignUpButtonIntent : LoginUiIntents
        data object OnLoginButtonIntent : LoginUiIntents
        data object OnForgotButtonIntent : LoginUiIntents

    }
    sealed interface TextFieldsIntent{
        @JvmInline
        value class OnEmailTextUpdate(val email:String) : LoginUiIntents
        @JvmInline
        value class OnPasswordTextUpdate(val password:String) : LoginUiIntents
    }

}