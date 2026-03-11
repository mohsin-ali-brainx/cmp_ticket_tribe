package com.brainx.ticket_tribe.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui.LoginScreen
import com.brainx.ticket_tribe.presentation.screens.auth.login.viewmodel.LoginViewModel
import com.brainx.utils_extensions.navigation.horizontallyAnimatedComposable
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation<AppRoutes.Auth>(
        startDestination = AuthRoutes.Login,
    ) {
        horizontallyAnimatedComposable<AuthRoutes.Login> {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreen(dataState = viewModel.state, uiEvents = viewModel.eventFlow){
                viewModel.onIntent(it)
            }
        }

//        horizontallyAnimatedComposable<AuthRoutes.SignUp> {
//            val viewModel = koinViewModel<SignUpViewModel>()
//            SignUpScreen(navController = navController, dataState = viewModel.state, uiEvents = viewModel.eventFlow){
//                viewModel.onIntent(it)
//            }
//        }
    }
}