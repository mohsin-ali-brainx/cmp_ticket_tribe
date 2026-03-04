package com.brainx.ticket_tribe.presentation.screens.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.domain.pref_manager.DatastorePrefManager
import com.brainx.ticket_tribe.presentation.navigation.AppRoutes
import com.brainx.ticket_tribe.presentation.navigation.SplashRoutes
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_logo

@Composable
fun SplashScreen(
    onNavigate:(AppRoutes)->Unit
) {
    val pref = koinInject<DatastorePrefManager>()

    val scope = rememberCoroutineScope()

    DisposableEffect(key1 = Unit) {
        val job =  scope.launch {
            val isFirstTimeInstalled =  pref.getIsFirstTimeInstalled()
            delay(ExtConstants.LongConstants.SECOND)
            onNavigate( if (!isFirstTimeInstalled)  SplashRoutes.Onboarding else AppRoutes.Auth )
        }
        onDispose {
            job.cancel()
        }
    }

    SplashScreenContent()
}

@Composable
private fun SplashScreenContent(){
    val localTheme = LocalAppTheme.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(localTheme.background.greyGradientColor2)

    ){padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(localTheme.background.onboardingGradient),
            ).padding(padding),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(Res.drawable.ic_logo), contentDescription = "")
        }
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_9
)
@Composable
private fun PreviewSplashScreen_PIXEL_9(){
    AppTheme {
        SplashScreenContent()
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_3
)
@Composable
private fun PreviewSplashScreen_PIXEL_3(){
    AppTheme {
        SplashScreenContent()
    }
}