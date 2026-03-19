package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_intents

import kotlin.jvm.JvmInline

sealed interface SignupUiIntents {
    sealed interface ButtonIntents{
        data object OnLoginButtonIntent : SignupUiIntents
        data object OnSignupButtonIntent : SignupUiIntents

    }
    sealed interface TextFieldsIntent{
        @JvmInline
        value class OnFirstNameTextUpdate(val firstName:String) : SignupUiIntents
        @JvmInline
        value class OnLastNameTextUpdate(val lastName:String) : SignupUiIntents
        @JvmInline
        value class OnEmailTextUpdate(val email:String) : SignupUiIntents
        @JvmInline
        value class OnPasswordTextUpdate(val password:String) : SignupUiIntents
        @JvmInline
        value class OnConfirmPasswordTextUpdate(val confirmPassword:String) : SignupUiIntents
    }

}