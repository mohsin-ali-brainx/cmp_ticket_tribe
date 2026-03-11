package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay

@Immutable
data class FormValidityState(
    val isValid:Boolean,
    val errorText: CustomTextToDisplay
)
