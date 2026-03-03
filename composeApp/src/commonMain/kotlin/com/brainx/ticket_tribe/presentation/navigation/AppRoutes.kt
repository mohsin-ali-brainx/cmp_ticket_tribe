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
    data object Splash: SplashRoutes
    @Serializable
    data object Onboarding: SplashRoutes
}

sealed interface AuthRoutes {
    @Serializable
    data object Login: AuthRoutes
    @Serializable
    data object SignUp: AuthRoutes
    @Serializable
    data object ForgotPassword: AuthRoutes
    @Serializable
    data object ResetPassword: AuthRoutes
    @Serializable
    data object SetPassword: AuthRoutes
    @Serializable
    data object ProfileSetup: AuthRoutes
    @Serializable
    data object OTP: AuthRoutes
    @Serializable
    data object AccountCreated: AuthRoutes
}