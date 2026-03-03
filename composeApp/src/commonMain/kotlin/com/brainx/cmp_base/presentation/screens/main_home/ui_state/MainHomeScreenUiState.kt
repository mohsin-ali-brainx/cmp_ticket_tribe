package com.brainx.cmp_base.presentation.screens.main_home.ui_state

import androidx.compose.runtime.Stable
import com.brainx.utils_extensions.constants.ExtConstants

@Stable
data class MainHomeScreenUiState(
    val searchText: String = ExtConstants.StringConstants.EMPTY,
    val isLoading: Boolean = false
)