package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_state

import com.brainx.ticket_tribe.utils.enums.CountryCode
import com.brainx.utils_extensions.constants.ExtConstants

data class SignupUiState(
    val firstNameText:String= ExtConstants.StringConstants.EMPTY,
    val lastNameText:String= ExtConstants.StringConstants.EMPTY,
    val emailText:String= ExtConstants.StringConstants.EMPTY,
    val passwordText:String= ExtConstants.StringConstants.EMPTY,
    val confirmPasswordText:String= ExtConstants.StringConstants.EMPTY,
    val userNameText:String= ExtConstants.StringConstants.EMPTY,
    val phoneText:String= ExtConstants.StringConstants.EMPTY,
    val locationText:String= ExtConstants.StringConstants.EMPTY,
    val isTermsChecked:Boolean=false,
    val isNextFormButtonValid:Boolean=false,
    val isSignupProfileFormButtonValid:Boolean=false,
    val isSignUpLoading:Boolean=false,
    val selectedCountryCode:CountryCode = CountryCode.US
)