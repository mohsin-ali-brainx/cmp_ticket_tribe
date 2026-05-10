package com.brainx.ticket_tribe.utils.validators

import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import com.brainx.utils_extensions.extensions.isValidUsername
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.valid_username

class UsernameValidator {
    operator fun invoke(text:String,ignoreEmpty:Boolean=false): FormValidityState {
        text.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, UiText.StringResourceText(Res.string.valid_username))
            if (isNotBlank() && !trim().isValidUsername()) return FormValidityState(false,UiText.StringResourceText(Res.string.valid_username))
        }
        return FormValidityState(true,UiText.StringText(ExtConstants.StringConstants.EMPTY))
    }
}