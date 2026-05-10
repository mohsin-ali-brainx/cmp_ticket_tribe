package com.brainx.ticket_tribe.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.buildAppTheme

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val appColorTheme = remember(darkTheme) { buildAppTheme(darkTheme) }

    CompositionLocalProvider(
        LocalAppTheme provides appColorTheme
    ){
        MaterialTheme(
            typography = appTypography(),
            colorScheme = lightColorScheme()
                .copy(surfaceContainer = Color.Transparent,
                    surfaceTint = Color.Transparent,
                    surfaceVariant = Color.Transparent,
                    surface = Color.Transparent),
            content = content
        )
    }
}