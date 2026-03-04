package com.brainx.ticket_tribe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.brainx.ticket_tribe.presentation.screens.onboarding.ui.OnboardingScreen
import com.brainx.ticket_tribe.presentation.screens.onboarding.viewmodel.OnboardingViewModel
import com.brainx.ticket_tribe.presentation.screens.splash.ui.SplashScreen
import com.brainx.utils_extensions.navigation.horizontallyAnimatedComposable
import com.brainx.utils_extensions.navigation.navigateSequentially
import com.brainx.utils_extensions.navigation.safeNavToNextScreen
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


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
            SplashScreen(
                onNavigate = {
                    navController.safeNavToNextScreen(it, shouldClearTop = true)

                }
            )
        }
        horizontallyAnimatedComposable<SplashRoutes.Onboarding>{
            val viewModel = koinViewModel<OnboardingViewModel>()
            val scope = rememberCoroutineScope()
            OnboardingScreen(
                uiEvents = viewModel.eventFlow,
                onIntent = {
                    viewModel.onIntent(it)
                },
                onNavigate = { route, shouldClearBackStack ->
                    navController.safeNavToNextScreen(route, shouldClearBackStack)
                },
                onNavigateSequentially = { firstRoute, secondRoute, shouldClearBackStack ->
                    scope.launch {
                        navController.navigateSequentially(
                            firstRoute = firstRoute,
                            secondRoute = secondRoute,
                            shouldClearBackStack = shouldClearBackStack
                        )
                    }
                },
                onBackPress = {
                    navController.popBackStack()
                })
        }
    }
}


