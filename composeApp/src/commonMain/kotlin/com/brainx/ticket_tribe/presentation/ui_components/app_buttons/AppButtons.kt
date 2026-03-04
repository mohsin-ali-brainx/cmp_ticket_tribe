package com.brainx.ticket_tribe.presentation.ui_components.app_buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.button.CustomButton
import com.brainx.ticket_tribe.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay

@Composable
fun PrimaryBlackButton(
    modifier: Modifier = Modifier,
    buttonText: CustomTextToDisplay.StringResourceText,
    isEnable:Boolean=true,
    borderColor: Color = LocalAppTheme.current.button.primaryBorderColor,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: () -> Unit
){
    val isButtonEnable by remember(isEnable) { derivedStateOf { isEnable } }
    val appTheme = LocalAppTheme.current
    CustomButton(
        modifier = modifier.then(Modifier.defaultFullWidthButtonModifier()),
        buttonText = buttonText,
        textColor = appTheme.button.secondaryWhiteTextColor,
        buttonColor = if (isButtonEnable) appTheme.button.primaryColor else appTheme.button.disable,
        fontSize = AppDimens.Fonts.font18,
        fontWeight = FontWeight.W400,
        borderColor = borderColor,
        borderWidth = AppDimens.Button.borderWidthHalf,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isClickable = isButtonEnable,
        onClickAction = onClickAction
    )
}
@Composable
fun SecondaryWhiteButton(
    modifier: Modifier = Modifier,
    buttonText:CustomTextToDisplay.StringResourceText,
    isEnable:Boolean=true,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: () -> Unit
){
    val isButtonEnable by remember { derivedStateOf{ isEnable } }
    val appTheme = LocalAppTheme.current

    CustomButton(
        modifier = modifier.then(Modifier.defaultFullWidthButtonModifier()),
        buttonText = buttonText,
        textColor = appTheme.button.primaryBlackTextColor,
        borderColor = appTheme.button.secondaryBorderColor,
        buttonColor = appTheme.button.secondaryColor,
        fontSize = AppDimens.Fonts.font18,
        fontWeight = FontWeight.W400,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onClickAction = onClickAction
    )
}
