package com.brainx.ticket_tribe.presentation.ui_components.app_buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.button.CustomButton
import com.brainx.ticket_tribe.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.log_in

@Composable
fun PrimaryBlackButton(
    modifier: Modifier = Modifier,
    buttonText: CustomTextToDisplay.StringResourceText,
    isEnable:Boolean=true,
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
        borderColor = LocalAppTheme.current.button.primaryBorderColor,
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
        buttonColor = if (isButtonEnable) appTheme.button.secondaryColor else appTheme.button.disable ,
        fontSize = AppDimens.Fonts.font18,
        fontWeight = FontWeight.W400,
        borderWidth = AppDimens.Button.borderWidthHalf,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isClickable = isButtonEnable,
        onClickAction = onClickAction
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000, heightDp = 100, widthDp = 400)
@Composable
fun PrimaryButtonPreview(){
    AppTheme {
        PrimaryBlackButton(
            buttonText = CustomTextToDisplay.StringResourceText(text = Res.string.log_in),
            onClickAction = {

            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 100, widthDp = 400)
@Composable
fun SecondaryButtonPreview(){
    AppTheme {
        SecondaryWhiteButton(
            buttonText = CustomTextToDisplay.StringResourceText(text = Res.string.log_in),
            onClickAction = {

            }
        )
    }
}
