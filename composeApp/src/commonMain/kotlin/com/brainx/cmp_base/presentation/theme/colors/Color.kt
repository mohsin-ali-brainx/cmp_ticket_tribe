package com.brainx.cmp_base.presentation.theme.colors
import androidx.compose.runtime.staticCompositionLocalOf


internal fun buildAppTheme(isDark: Boolean): AppColorTheme {
    return if (isDark) {
        AppDarkThemeColor()
    } else {
        AppLightThemeColor()
    }
}

val LocalAppTheme = staticCompositionLocalOf<AppColorTheme> {
    error("LocalAppTheme not provided. Wrap your UI in AppTheme().")
}

