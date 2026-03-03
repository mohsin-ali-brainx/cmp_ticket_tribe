package com.brainx.cmp_base.presentation.screens.main_home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.brainx.cmp_base.presentation.navigation.AppRoutes
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.ui_components.button.CustomButton
import com.brainx.cmp_base.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.cmp_base.presentation.ui_components.text.CustomText
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple

@Composable
fun MainHomeScreen(
    onNavigate:(AppRoutes)->Unit
) {
    MainContent(onNavigate)
}

@Composable
private fun MainContent(
    onNavigate: (AppRoutes) -> Unit
){
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val keyboardHeight = WindowInsets.ime.getBottom(density = LocalDensity.current)

    val appThemeColor =  LocalAppTheme.current

    Scaffold(
        modifier = Modifier.background(appThemeColor.background.backgroundColor)
            .fillMaxSize()
            .imePadding()
            .clickableSingleWithoutRipple {
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(appThemeColor.background.backgroundColor)
                .padding(paddingValues)
        ) {
            val items = listOf(
                "Buttons" to AppRoutes.ButtonsDemo,
                "Filled text fields" to AppRoutes.TextFieldsDemo,
                "Underline text fields" to AppRoutes.UnderlineTextFieldsDemo,
                "Text" to AppRoutes.TextDemo,
                "Pickers" to AppRoutes.PickersDemo
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appThemeColor.background.backgroundColor)
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    CustomText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        text = CustomTextToDisplay.StringText("Component playground"),
                        fontSize = AppDimens.Fonts.font20,
                        color = appThemeColor.textView.primaryBlackTextColor
                    )
                }

                items(items) { (label, route) ->
                    CustomButton(
                        modifier = Modifier.defaultFullWidthButtonModifier(),
                        buttonText = CustomTextToDisplay.StringText(label),
                        buttonColor = appThemeColor.button.primaryColor,
                        textColor = appThemeColor.button.secondaryWhiteTextColor,
                        onClickAction = { onNavigate(route) }
                    )
                }
            }
        }
    }
}

