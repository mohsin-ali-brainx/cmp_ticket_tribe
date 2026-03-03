package com.brainx.ticket_tribe.presentation.screens.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_logo

@Composable
fun SplashScreen() {
    SplashScreenContent()
}

@Composable
private fun SplashScreenContent(){
    val localTheme = LocalAppTheme.current
    Scaffold(
        modifier = Modifier
            .background(localTheme.background.greyGradientColor2)
            .fillMaxSize()
    ){
        Box(modifier = Modifier
            .background(
                brush = Brush.verticalGradient(localTheme.background.onboardingGradient),
            )
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(Res.drawable.ic_logo), contentDescription = "")
        }
    }
}