package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_intents

import com.brainx.ticket_tribe.utils.enums.CountryCode
import kotlin.jvm.JvmInline

sealed interface SignupUiIntents {
    sealed interface ButtonIntents{
        data object OnLoginButtonIntent : SignupUiIntents
        data object OnSignupButtonIntent : SignupUiIntents
        data object OnNextButtonIntent : SignupUiIntents


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
        @JvmInline
        value class OnUserNameTextUpdate(val username:String) : SignupUiIntents

        @JvmInline
        value class OnPhoneTextUpdate(val phone:String) : SignupUiIntents
        @JvmInline
        value class OnLocationTextUpdate(val location:String) : SignupUiIntents
        @JvmInline
        value class OnCountryCodeSelected(val countryCode: CountryCode) : SignupUiIntents
    }

    sealed interface CheckBoxIntent{
        data object OnTermsCheckboxIntent : SignupUiIntents

    }

}