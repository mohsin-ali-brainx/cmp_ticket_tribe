package com.brainx.utils_extensions.compose_ui_utils.modifiers

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

actual fun Modifier.customNavigationBarsPadding(): Modifier =
    this.padding(bottom = 16.dp)

