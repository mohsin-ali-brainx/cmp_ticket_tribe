package com.brainx.ticket_tribe.presentation.theme

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
        val padding20 = 20.0.dp
        val padding24 = 24.0.dp
        val padding32 = 32.0.dp

        val padding36 = 36.0.dp
        val padding42 = 42.0.dp


    }

    data object Radius: AppDimens {

        val radius4 = 4.0.dp
        val radius6 = 6.0.dp
        val radius8 = 8.0.dp
        val radius12 = 12.0.dp
        val radius14 = 14.0.dp
        val radius16 = 16.0.dp
        val radius20 = 20.0.dp
        val radius30 = 30.0.dp
        val radius50 = 50.0.dp
        val radius100 = 100.0.dp
        val bottomSheetRadius = 20.0.dp
    }

    data object Margins: AppDimens {

    }

    data object Button: AppDimens {
        val zero = 0.dp
        val defaultButtonHeight = 60.0.dp
        val callForOrderHeight = 42.0.dp
        val ratingsViewHeight = 50.0.dp
        val defaultIconButtonSize = 40.0.dp
        val smallButtonHeight = 40.0.dp
        val xSmallButtonHeight = 30.0.dp
        val xxSmallButtonHeight = 25.0.dp
        val mediumButtonHeight = 50.0.dp
        val trackOrderButtonHeight = 40.0.dp
        val viewAllButtonWidth = 125.0.dp
        val viewTicketButtonWidth = 100.0.dp
        val viewTicketButtonHeight = 25.0.dp
        val addGroupButtonHeight = 110.0.dp
        val addGroupButtonWidth = 80.0.dp
        val circularIconButtonSize = 80.0.dp
        val borderWidthHalf = 0.5.dp
        val disableButton = 0.5f
        val backIconButton = 30.0.dp

    }

    data object EditText: AppDimens {
        val defaultTextFieldHeight = 60.0.dp
        val smallTextFieldHeight = 35.0.dp
        val defaultCommentTextFieldHeight = 150.0.dp
        val defaultMultiTextFieldHeight = 140.0.dp
        val searchTextFieldHeight = 55.0.dp
        val otpDefaultWidth = 60.0.dp
        val mapSearchBarHeight = 50.0.dp
        val artistProfileSearchbarHeight = 40.0.dp
        val suggestionsSearchResultHeight = 150.0.dp
        val profileFollowerSearchbarHeight = 40.0.dp
        val borderWidth = 1.dp

    }



    data object Icons: AppDimens {
        val xxSmallIconSize = 12.0.dp
        val xSmallIconSize = 16.0.dp
        val smallIconSize = 20.0.dp
        val defaultIconSize = 24.0.dp
        val defaultIconSizePadding = 30.0.dp
        val filterIconSize = 40.0.dp
        val loaderSize = 40.0.dp
        val mediumIconSize = 30.0.dp
        val largeIconSize = 72.0.dp
        val dotSize8 = 8.0.dp
        val dotSize12 = 12.0.dp
    }


    data object ShapeDimens: AppDimens{
        val defaultEditTextCornerRadius = 8.dp
    }

    data object Fonts: AppDimens{
        val font6 = 6.sp
        val font8 = 8.sp
        val font12 = 12.sp
        val font11 = 11.sp
        val font10 = 10.sp
        val font13 = 13.sp
        val font14 = 14.sp
        val font16 = 16.sp
        val font18 = 18.sp
        val font20 = 20.sp
        val font22 = 22.sp
        val font24 = 24.sp
        val font26 = 26.sp
        val font28 = 28.sp
        val font30 = 30.sp
        val font32 = 32.sp
        val font62 = 62.sp
        val font72 = 72.sp


    }

    data object AppBar: AppDimens {
        val tabBarHeight = 40.0.dp
        val appBarPreferredHeight = 60.0.dp
        val searchAppBarPreferredHeight = 60.0.dp
        val appBarPreferredHeight2 = 65.0.dp
        val appBarPreferredHeight3 = 50.0.dp
    }

    data object Card: AppDimens {
        val elevation2 = 2.0.dp
        val elevation4 = 4.0.dp
        val elevation8 = 8.0.dp
    }

    data object Dividers: AppDimens {
        val dividerHeight20 = 20.0.dp
        val dividerHeight1 = 1.0.dp
        val dividerHeight2 = 2.0.dp
        val dividerHeight = 5.0.dp
        val dividerWidth = 50.0.dp
    }


    data object Images: AppDimens {
        val artistProfileImageSize = 127.0.dp
        val artistProfileImageSmallSize = 55.0.dp
        val defaultAvatarSize = 80.0.dp
        val profileAppBarSize = 100.0.dp
        val smallProfileAvatar = 36.0.dp
        val profileAvatar = 50.0.dp
        val settingsProfileAvatar = 60.0.dp
        val profilePictureSize = 80.0.dp
        val categoryCircularImageSize = 65.0.dp
    }

    data object ListItems:AppDimens {
        val categoryHorizontalItemHeight = 85.0.dp
        val categoryHorizontalItemWidth = 135.0.dp
        val productHorizontalGridItemHeight = 180.0.dp
        val productHorizontalItemHeight = 180.0.dp
        val productGridItemHeight = 180.0.dp
        val productGridItemWidth = 200.0.dp
        val horizontalTicketsListViewHeight = 110.0.dp
        val horizontalTicketListViewWidth = 200.0.dp
        val horizontalTicketWidth = 185.0.dp
        val horizontalTicketHeight = 65.0.dp
        val horizontalCommunityListViewHeight = 160.0.dp
        val horizontalCommunityItemHeight = 110.0.dp
        val horizontalCommunityItemWidth = 186.0.dp
        val horizontalEventListViewWidth = 365.0.dp
        val horizontalEventListViewHeight = 245.0.dp
        val feedImageHeight = 137.0.dp
        val horizontalArtistListViewWidth = 190.0.dp
        val horizontalArtistListViewHeight = 150.0.dp
        val horizontalArtistItemWidth = 184.0.dp
        val horizontalArtistItemHeight = 118.0.dp
        val horizontalPopularEventListViewWidth = 350.0.dp
        val horizontalPopularEventListViewHeight = 245.0.dp
        val horizontalPopularEventItemWidth = 350.0.dp
        val horizontalPopularEventItemHeight = 200.0.dp
        val horizontalCategoryListViewWidth = 155.0.dp
        val horizontalCategoryListViewHeight = 200.0.dp
        val horizontalCategoryItemWidth = 150.0.dp
        val horizontalCategoryItemHeight = 160.0.dp
        val verticalEventListViewHeight = 250.0.dp
        val cartProductItemImageSize = 75.0.dp
        val productItemsHeight = 200.0.dp
        val smallProductItemsHeight = 70.0.dp
        val productCompanyHeight = 35.0.dp
        val productCompanyWidth = 60.0.dp
        val notificationTileHeight = 70.0.dp
    }


}