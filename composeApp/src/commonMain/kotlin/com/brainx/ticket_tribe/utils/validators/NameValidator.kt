package com.brainx.ticket_tribe.utils.validators

import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import com.brainx.utils_extensions.extensions.isValidEmail
import org.jetbrains.compose.resources.StringResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.please_enter_valid_email
import tickettribecmp.composeapp.generated.resources.required_field

class NameValidator{
    operator fun invoke(text:String, errorMessage: StringResource?=null, ignoreEmpty:Boolean=false): FormValidityState {
        text.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, UiText.StringResourceText(errorMessage?:Res.string.required_field))
            if (isNotBlank()) return FormValidityState(false,UiText.StringResourceText(errorMessage?:Res.string.required_field))
        }
        return FormValidityState(true,UiText.StringText(ExtConstants.StringConstants.EMPTY))
    }
}