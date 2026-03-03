package com.brainx.cmp_base.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.brainx.cmp_base.presentation.screens.main_home.ui.MainHomeScreen
import com.brainx.cmp_base.presentation.screens.component_playground.ComponentButtonsScreen
import com.brainx.cmp_base.presentation.screens.component_playground.ComponentTextFieldsScreen
import com.brainx.cmp_base.presentation.screens.component_playground.ComponentUnderlineTextFieldsScreen
import com.brainx.cmp_base.presentation.screens.component_playground.ComponentTextScreen
import com.brainx.cmp_base.presentation.screens.component_playground.ComponentPickersScreen
import com.brainx.utils_extensions.navigation.safeNavToNextScreen


@Composable
fun AppNavHostGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.MainHome
    ) {

        mainHomeScreenRoute(navController = navController)
        buttonsDemoRoute(navController = navController)
        textFieldsDemoRoute(navController = navController)
        underlineTextFieldsDemoRoute(navController = navController)
        textDemoRoute(navController = navController)
        pickersDemoRoute(navController = navController)
    }
}

private fun NavGraphBuilder.mainHomeScreenRoute(
    navController: NavHostController
){
    composable<AppRoutes.MainHome> { backStackEntry ->

        MainHomeScreen(
            onNavigate = {
                when(it){
                    AppRoutes.ButtonsDemo -> navController.safeNavToNextScreen(AppRoutes.ButtonsDemo)
                    AppRoutes.TextFieldsDemo -> navController.safeNavToNextScreen(AppRoutes.TextFieldsDemo)
                    AppRoutes.UnderlineTextFieldsDemo -> navController.safeNavToNextScreen(AppRoutes.UnderlineTextFieldsDemo)
                    AppRoutes.TextDemo -> navController.safeNavToNextScreen(AppRoutes.TextDemo)
                    AppRoutes.PickersDemo -> navController.safeNavToNextScreen(AppRoutes.PickersDemo)
                    else -> Unit
                }
            }
        )
    }
}

private fun NavGraphBuilder.buttonsDemoRoute(
    navController: NavHostController
) {
    composable<AppRoutes.ButtonsDemo> {
        ComponentButtonsScreen()
    }
}

private fun NavGraphBuilder.textFieldsDemoRoute(
    navController: NavHostController
) {
    composable<AppRoutes.TextFieldsDemo> {
        ComponentTextFieldsScreen()
    }
}

private fun NavGraphBuilder.underlineTextFieldsDemoRoute(
    navController: NavHostController
) {
    composable<AppRoutes.UnderlineTextFieldsDemo> {
        ComponentUnderlineTextFieldsScreen()
    }
}

private fun NavGraphBuilder.textDemoRoute(
    navController: NavHostController
) {
    composable<AppRoutes.TextDemo> {
        ComponentTextScreen()
    }
}

private fun NavGraphBuilder.pickersDemoRoute(
    navController: NavHostController
) {
    composable<AppRoutes.PickersDemo> {
        ComponentPickersScreen()
    }
}
