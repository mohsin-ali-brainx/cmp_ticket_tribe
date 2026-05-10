package com.brainx.ticket_tribe.presentation.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed interface AppRoutes {
    @Serializable
    data object Splash: AppRoutes
    @Serializable
    data object Auth: AppRoutes
}

sealed interface SplashRoutes {
    @Serializable
    data object Splash: AppRoutes
    @Serializable
    data object Onboarding: AppRoutes
}

sealed interface AuthRoutes {
    @Serializable
    data object Login: AppRoutes
    @Serializable
    data object SignUp: AppRoutes
    @Serializable
    data object ForgotPassword: AppRoutes
    @Serializable
    data object ResetPassword: AppRoutes
    @Serializable
    data object SetPassword: AppRoutes
    @Serializable
    data object ProfileSetup: AppRoutes
    @Serializable
    data object OTP: AppRoutes
    @Serializable
    data object AccountCreated: AppRoutes
}