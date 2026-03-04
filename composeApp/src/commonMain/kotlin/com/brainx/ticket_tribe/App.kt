package com.brainx.ticket_tribe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.brainx.ticket_tribe.presentation.navigation.AppNavHostGraph
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.utils_extensions.constants.ExtConstants


@Composable
@Preview
fun App() {

    AppTheme {
        val navController = rememberNavController()

        Scaffold(
            Modifier.background(color = LocalAppTheme.current.background.backgroundColor),
            contentWindowInsets = WindowInsets(
            left= ExtConstants.IntegerConstants.ZERO,
            top= ExtConstants.IntegerConstants.ZERO,
            right=ExtConstants.IntegerConstants.ZERO,
            bottom=ExtConstants.IntegerConstants.ZERO),
        ) {
            AppNavHostGraph(navController = navController)
        }
    }
}