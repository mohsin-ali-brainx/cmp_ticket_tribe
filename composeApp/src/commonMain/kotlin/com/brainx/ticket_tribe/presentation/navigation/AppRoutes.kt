package com.brainx.ticket_tribe.presentation.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class AppRoutes {
    @Serializable
    data object Splash : AppRoutes()
}