package com.brainx.utils_extensions.compose_ui_utils.modifiers

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Modifier

actual fun Modifier.customNavigationBarsPadding(): Modifier =
    this.navigationBarsPadding()

