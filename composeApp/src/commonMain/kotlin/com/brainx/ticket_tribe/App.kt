package com.brainx.ticket_tribe

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.brainx.ticket_tribe.presentation.navigation.AppNavHostGraph
import com.brainx.ticket_tribe.presentation.theme.AppTheme


@Composable
@Preview
fun App() {

    AppTheme {
        val navController = rememberNavController()

        Scaffold(
            Modifier.background(color = MaterialTheme.colorScheme.background)
        ) {
            AppNavHostGraph(navController = navController)
        }
    }
}