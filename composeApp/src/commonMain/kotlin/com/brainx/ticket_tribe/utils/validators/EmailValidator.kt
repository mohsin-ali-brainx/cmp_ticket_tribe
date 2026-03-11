package com.brainx.ticket_tribe.utils.validators

import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.constants.ExtConstants
import com.brainx.utils_extensions.extensions.isValidEmail
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.please_enter_valid_email

class EmailValidator{
    operator fun invoke(email:String,ignoreEmpty:Boolean=false): FormValidityState {
        email.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, CustomTextToDisplay.StringResourceText(Res.string.please_enter_valid_email))
            if (isNotBlank() && !trim().isValidEmail()) return FormValidityState(false,CustomTextToDisplay.StringResourceText(Res.string.please_enter_valid_email))
        }
        return FormValidityState(true,CustomTextToDisplay.StringText(ExtConstants.StringConstants.EMPTY))
    }
}