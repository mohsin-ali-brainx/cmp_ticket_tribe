package com.brainx.utils_extensions.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.safeNavToNextScreen(route: Any, shouldNavigateUp: Boolean = false, shouldClearTop: Boolean = false, shouldClearBackStack: Boolean = false) {

    if (shouldNavigateUp)
    {
        navigateUp()
        navigate(route)
        return
    }
    if (shouldClearBackStack) {
        navigate(route) {
            // Clear the entire back stack
            popUpTo(0) { inclusive = true }
        }
        return
    }
    if (shouldClearTop){
        navigate(route) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
        return
    }
    navigate(route)
}

fun NavHostController.safeNavToPreviousScreenWithResult(newRoute: Any,currentRoute:Any){
    navigate(newRoute){
        launchSingleTop = true
        popUpTo(currentRoute::class){ inclusive=true }
    }
}

fun <T> NavHostController.navigateToNextScreen(route: Any, list: List<Pair<String, T?>> = arrayListOf(), shouldPopBackStack: Boolean = true) {
    list.forEach {
        currentBackStackEntry?.savedStateHandle?.apply {
            set(it.first, it.second)
        }
    }
    if (shouldPopBackStack) navigateUp()
    navigate(route)
}

fun <T> NavHostController.navigateToBackWithResult(list: List<Pair<String, T?>> = arrayListOf()) {
    navigateUp()
    list.forEach {
        currentBackStackEntry?.savedStateHandle?.apply {
            set(it.first, it.second)
        }
    }
}

fun <T> NavHostController.navigateToNextScreen(route: Any, list: List<Pair<String, T?>> = arrayListOf(), shouldPopBackStack: Boolean = true,shouldClearTop:Boolean=false) {
    list.forEach {
        currentBackStackEntry?.savedStateHandle?.apply {
            set(it.first, it.second)
        }
    }
    if (shouldPopBackStack) navigateUp()
    if (shouldClearTop){
        navigate(route){
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }else{
        navigate(route)
    }
}

// Perform sequential navigation to handle navigating to a nested route
// This function directly navigates to the Auth graph's SignUp screen
suspend fun NavHostController.navigateSequentially(
    firstRoute: Any,
    secondRoute: Any,
    delayMs: Long = 10,
    shouldPopBackStack:Boolean=false,
    shouldClearBackStack: Boolean = false,
    shouldClearTop: Boolean = false
) {
    // First navigate to the parent route
    safeNavToNextScreen(firstRoute, shouldNavigateUp = shouldPopBackStack, shouldClearBackStack = shouldClearBackStack, shouldClearTop = shouldClearTop)

    // Wait to ensure the first navigation completes
    kotlinx.coroutines.delay(delayMs)

    // Then navigate to the nested route
    safeNavToNextScreen(secondRoute, shouldNavigateUp = shouldPopBackStack, shouldClearBackStack = shouldClearBackStack, shouldClearTop = shouldClearTop)
}
