package com.brainx.cmp_base.presentation.screens.main_home.ui_intents


import kotlin.jvm.JvmInline


sealed interface MainHomeScreenUiIntents {
    sealed interface ButtonIntents{
        data object OnSearchButtonIntent : MainHomeScreenUiIntents
    }
    sealed interface TextFieldsIntent{
        @JvmInline
        value class OnSearchTextUpdate(val search:String) : MainHomeScreenUiIntents
    }
    sealed interface ListItemIntent{

        data object OnMovieItemClick : MainHomeScreenUiIntents

        data object OnTriggerPagination : MainHomeScreenUiIntents
    }


}