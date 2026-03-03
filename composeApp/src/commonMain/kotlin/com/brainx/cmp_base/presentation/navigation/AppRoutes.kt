package com.brainx.cmp_base.presentation.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class AppRoutes {
    @Serializable
    data object MainHome : AppRoutes()

    @Serializable
    data object ButtonsDemo : AppRoutes()

    @Serializable
    data object TextFieldsDemo : AppRoutes()

    @Serializable
    data object UnderlineTextFieldsDemo : AppRoutes()

    @Serializable
    data object TextDemo : AppRoutes()

    @Serializable
    data object PickersDemo : AppRoutes()

    @Serializable
    data class Detail(val mediaDataModelJson: String) : AppRoutes()

    @Serializable
    data class VideoPlayer(
        val id:Int,
        val mediaType: String
    ) : AppRoutes()
}