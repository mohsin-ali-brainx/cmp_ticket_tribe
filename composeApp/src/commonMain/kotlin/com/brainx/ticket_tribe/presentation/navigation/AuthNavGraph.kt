package com.brainx.ticket_tribe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui.LoginScreen
import com.brainx.ticket_tribe.presentation.screens.auth.login.viewmodel.LoginViewModel
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui.SignupProfileSetupScreen
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui.SignupScreen
import com.brainx.ticket_tribe.presentation.screens.auth.signup.viewmodel.SignupViewModel
import com.brainx.utils_extensions.navigation.horizontallyAnimatedComposable
import com.brainx.utils_extensions.navigation.safeNavToNextScreen
import org.koin.compose.viewmodel.koinViewModel
import com.brainx.utils_extensions.shared_viewmodel.sharedKoinViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation<AppRoutes.Auth>(
        startDestination = AuthRoutes.Login,
    ) {
        horizontallyAnimatedComposable<AuthRoutes.Login> {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreen(
                dataState = viewModel.state,
                uiEvents = viewModel.eventFlow,
                onIntent = { viewModel.onIntent(it)},
                onNavigate = { route, shouldClearBackStack ->
                    navController.safeNavToNextScreen(route, shouldClearBackStack = shouldClearBackStack)
                }
            )
        }

        horizontallyAnimatedComposable<AuthRoutes.SignUp> {
            val viewModel = it.sharedKoinViewModel<SignupViewModel>(navController)

            SignupScreen(
                dataState = viewModel.state,
                uiEvents = viewModel.eventFlow,
                onIntent = { viewModel.onIntent(it) },
                onNavigate = { route, shouldClearBackStack ->
                    navController.safeNavToNextScreen(route, shouldClearBackStack =shouldClearBackStack)
                }
            )
        }

        horizontallyAnimatedComposable<AuthRoutes.ProfileSetup> {
            val viewModel = it.sharedKoinViewModel<SignupViewModel>(navController)

            SignupProfileSetupScreen(
                dataState = viewModel.state,
                uiEvents = viewModel.eventFlow,
                onIntent = { viewModel.onIntent(it) },
                onNavigate = { route, shouldClearBackStack ->
                    navController.safeNavToNextScreen(route, shouldClearBackStack =shouldClearBackStack)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }



    }
}