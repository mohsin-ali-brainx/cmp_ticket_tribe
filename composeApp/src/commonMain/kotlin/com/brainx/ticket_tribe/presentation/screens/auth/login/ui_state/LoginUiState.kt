package com.brainx.ticket_tribe.presentation.screens.auth.login.ui_state

import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.StringResource


data class LoginUiState(
    val emailText:String= ExtConstants.StringConstants.EMPTY,
    val passwordText:String= ExtConstants.StringConstants.EMPTY,
    val isFormButtonValid:Boolean=false,
    val isEmailValid: Pair<Boolean,StringResource>? = null,
    val isPasswordValid: Pair<Boolean,StringResource>? = null
    )