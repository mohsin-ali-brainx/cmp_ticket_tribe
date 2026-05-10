package com.brainx.ticket_tribe.utils.validators

import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.ticket_tribe.utils.enums.CountryCode
import com.brainx.utils_extensions.constants.ExtConstants
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.please_enter_valid_phone
import kotlin.ranges.rangeTo

class PhoneNumberValidator {
    operator fun invoke(text:String,countryCode: CountryCode,ignoreEmpty:Boolean=false): FormValidityState {
        text.apply {
            if (!ignoreEmpty && isBlank()) return FormValidityState(false, UiText.StringResourceText(Res.string.please_enter_valid_phone))
            if (isNotBlank() && !trim().isValidPhoneNumber(countryCode)) return FormValidityState(false,UiText.StringResourceText(Res.string.please_enter_valid_phone))
        }
        return FormValidityState(true,UiText.StringText(ExtConstants.StringConstants.EMPTY))
    }
}

private fun String.isValidPhoneNumber(country: CountryCode): Boolean {
    val cleaned = this.replace(Regex("[^\\d]"), "")
    return cleaned.length in country.minLength..country.maxLength
}