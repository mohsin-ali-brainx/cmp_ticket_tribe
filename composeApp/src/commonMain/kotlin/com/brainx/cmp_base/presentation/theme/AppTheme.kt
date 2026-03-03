package com.brainx.cmp_base.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.colors.buildAppTheme

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
            typography = InterTypography(),
            content = content
        )
    }
}