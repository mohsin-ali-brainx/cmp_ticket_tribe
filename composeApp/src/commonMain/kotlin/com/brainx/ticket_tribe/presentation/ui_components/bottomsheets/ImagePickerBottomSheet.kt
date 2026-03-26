package com.brainx.ticket_tribe.presentation.ui_components.bottomsheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.TertiaryWhiteButtonWithTrailingIcon
import com.brainx.ticket_tribe.presentation.ui_components.button.CustomButton
import com.brainx.ticket_tribe.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.ticket_tribe.utils.enums.ImagePickerOptions
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.add_profile_image
import tickettribecmp.composeapp.generated.resources.ic_search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerBottomSheet(
    modifier: Modifier = Modifier,
    showSheet: Boolean,
    onOptionSelected:(ImagePickerOptions)->Unit,
    onDismiss: () -> Unit,
    ){
    if (!showSheet) return

    AppBottomSheet(
        showSheet = showSheet,
        onDismiss = onDismiss,
        modifier = modifier,
        showTitle = true,
        title = UiText.StringResourceText(Res.string.add_profile_image),
    ) {
        ImagePickerBottomSheetContent(onOptionSelected = {
            onOptionSelected(it)
            onDismiss()
        })
    }
}

@Composable
private fun ImagePickerBottomSheetContent(
    onOptionSelected:(ImagePickerOptions)->Unit
){
    val theme = LocalAppTheme.current
    LazyColumn(modifier = Modifier.padding(top = AppDimens.Padding.padding20)) {
        items(ImagePickerOptions.entries.size){ index->
            val item = ImagePickerOptions.entries[index]
            TertiaryWhiteButtonWithTrailingIcon(
                modifier = Modifier.padding(vertical = AppDimens.Padding.padding4, horizontal = AppDimens.Padding.padding20),
                buttonText = UiText.StringResourceText(text = item.textToDisplay),
                onClickAction = {
                    onOptionSelected(item)
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ImagePickerBottomSheetPreview(){
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()){
            ImagePickerBottomSheet(
                showSheet = true,
                onOptionSelected = {},
                onDismiss = {}
            )
        }
    }
}