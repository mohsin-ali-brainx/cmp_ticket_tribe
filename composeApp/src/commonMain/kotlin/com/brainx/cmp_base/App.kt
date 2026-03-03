package com.brainx.cmp_base

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.brainx.cmp_base.presentation.navigation.AppNavHostGraph
import com.brainx.cmp_base.presentation.theme.AppTheme


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