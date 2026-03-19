package com.brainx.ticket_tribe.utils.validators

import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import com.brainx.utils_extensions.extensions.isValidEmail
import com.brainx.utils_extensions.extensions.isValidPassword
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.password_does_not_match
import tickettribecmp.composeapp.generated.resources.please_enter_valid_email
import tickettribecmp.composeapp.generated.resources.please_enter_valid_password
import tickettribecmp.composeapp.generated.resources.strong

class PasswordValidator{
    operator fun invoke(password:String,ignoreEmpty:Boolean=false): FormValidityState {
        password.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, UiText.StringResourceText(Res.string.please_enter_valid_password))
            if (isBlank()) return FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            if (!trim().isValidPassword()) return FormValidityState(false,UiText.StringResourceText(Res.string.please_enter_valid_password))
        }
        return FormValidityState(true,UiText.StringResourceText(Res.string.strong))
    }
}

class ConfirmPasswordValidator{
    operator fun invoke(password:String, confirmPassword: String, ignoreEmpty:Boolean=false): FormValidityState {
        confirmPassword.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, UiText.StringResourceText(Res.string.please_enter_valid_password))
            if (isBlank()) return FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            if (!trim().isValidPassword()) return FormValidityState(false,UiText.StringResourceText(Res.string.please_enter_valid_password))
            if (trim() != password) return FormValidityState(false,UiText.StringResourceText(Res.string.password_does_not_match))
        }
        return FormValidityState(true,UiText.StringResourceText(Res.string.strong))
    }
}