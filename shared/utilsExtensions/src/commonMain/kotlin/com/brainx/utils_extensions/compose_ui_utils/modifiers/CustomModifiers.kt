package com.brainx.utils_extensions.compose_ui_utils.modifiers

import androidx.compose.ui.Modifier

/**
 * Platform-specific navigation bar padding.
 *
 * - **Android**: applies `Modifier.navigationBarsPadding()` so content sits above system nav bars.
 * - **iOS**: applies a fixed `32.dp` bottom padding to keep content away from the home indicator.
 */
expect fun Modifier.customNavigationBarsPadding(): Modifier

