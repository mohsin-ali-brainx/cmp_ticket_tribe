package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states

import androidx.compose.runtime.Immutable
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText

@Immutable
data class FormValidityState(
    val isValid:Boolean,
    val errorText: UiText
)
