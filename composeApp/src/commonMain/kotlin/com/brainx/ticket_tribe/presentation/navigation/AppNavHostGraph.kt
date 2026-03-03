package com.brainx.ticket_tribe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.brainx.ticket_tribe.presentation.screens.splash.ui.SplashScreen
import com.brainx.utils_extensions.navigation.horizontallyAnimatedComposable


@Composable
fun AppNavHostGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Splash
    ) {
        splashNavGraph(navController)
    }
}

private fun NavGraphBuilder.splashNavGraph(navController: NavHostController){
    navigation<AppRoutes.Splash>(
        startDestination = SplashRoutes.Splash,
    ) {
        horizontallyAnimatedComposable<SplashRoutes.Splash>{
            SplashScreen()
        }
        horizontallyAnimatedComposable<SplashRoutes.Onboarding>{
//            val viewModel = koinViewModel<OnboardingViewModel>()
//            OnboardingScreen(navController = navController, uiEvents = viewModel.eventFlow) {
//                viewModel.onIntent(it)
//            }
        }
    }
}


