package com.brainx.cmp_base.presentation.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed interface AppDimens{


    data object Padding: AppDimens {
        val zero = 0.dp
        val textPadding4 = 4.0.dp

        val iconPadding = 8.0.dp

        val padding4 = 4.0.dp
        val padding8 = 8.0.dp
        val padding12 = 12.0.dp
        val padding16 = 16.0.dp
    }

    data object Radius: AppDimens {

        val radius12 = 12.0.dp
        val radius16 = 16.0.dp
    }

    data object Margins: AppDimens {

    }

    data object Button: AppDimens {
        val zero = 0.dp
        val defaultButtonHeight =  58.dp
        val borderWidthHalf = 0.5.dp

    }

    data object EditText: AppDimens {
        val searchBarHeight = 58.dp
        val borderWidth = 1.dp

    }



    data object Icons: AppDimens {
        val loaderSize = 60.0.dp
    }



    data object ListItems: AppDimens {
    }



    data object ShapeDimens: AppDimens{
        val defaultEditTextCornerRadius = 8.dp
    }

    data object Fonts: AppDimens{
        val font12 = 12.sp
        val font16 = 16.sp
        val font18 = 18.sp
        val font20 = 20.sp
        val font24 = 24.sp


    }

}