package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_state

import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.StringResource

data class SignupUiState(
    val firstNameText:String= ExtConstants.StringConstants.EMPTY,
    val lastNameText:String= ExtConstants.StringConstants.EMPTY,
    val emailText:String= ExtConstants.StringConstants.EMPTY,
    val passwordText:String= ExtConstants.StringConstants.EMPTY,
    val confirmPasswordText:String= ExtConstants.StringConstants.EMPTY,
    val isFormButtonValid:Boolean=false,
    val isEmailValid: Pair<Boolean,StringResource>? = null,
    val isPasswordValid: Pair<Boolean,StringResource>? = null,
    val isSignUpLoading:Boolean=false
)