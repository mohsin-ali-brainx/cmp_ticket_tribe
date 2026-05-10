package com.brainx.ticket_tribe.presentation.ui_components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.compose_ui_utils.modifiers.customNavigationBarsPadding
import com.brainx.utils_extensions.constants.ExtConstants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = true,
    showDragHandle: Boolean = true,
    showTitle: Boolean = true,
    title: UiText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY),
    isFullScreen: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val theme = LocalAppTheme.current

    if (showSheet) {

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            containerColor = theme.background.whiteColor,
            shape = RoundedCornerShape(topStart = AppDimens.Radius.radius20, topEnd = AppDimens.Radius.radius20),
            dragHandle = null,
            contentWindowInsets = { WindowInsets(0, 0, 0, 0) },
            modifier = modifier
        ) {

            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth()
            ) {

                val screenHeight = maxHeight

                val maxSheetHeight = if (isFullScreen) {
                    screenHeight
                } else {
                    screenHeight * 0.8f
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxSheetHeight)
                        .customNavigationBarsPadding()
                        .padding(bottom = AppDimens.Padding.padding8),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (showDragHandle) {
                        DefaultDragHandle()
                    }
                    if (showTitle){
                        CustomText(modifier= Modifier
                            .padding(top = AppDimens.Padding.padding16),
                            text = title,
                            color = theme.textView.primaryBlackTextColor,
                            fontWeight = FontWeight.W200,
                            fontSize = AppDimens.Fonts.font20
                        )
                    }
                    content()
                }
            }
        }
    }
}

@Composable
private fun DefaultDragHandle() {
    val theme = LocalAppTheme.current

    Box(
        modifier = Modifier
            .padding(top = AppDimens.Padding.padding16)
            .width(AppDimens.Dividers.dividerWidth)
            .height(AppDimens.Dividers.dividerHeight)
            .clip(RoundedCornerShape(ExtConstants.IntegerConstants.FIFTY))
            .background(theme.divider.dividerColor4)
    )
}