package com.brainx.cmp_base.presentation.ui_components.text_fields

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.theme.appPrimaryFontFamily
import com.brainx.cmp_base.presentation.theme.colors.EditTextColors

@Composable
fun editTextStyle(
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textColor: Color = LocalAppTheme.current.editText.tertiaryWhiteTextColor,
    textAlign: TextAlign = TextAlign.Start
)= TextStyle(
    textColor,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign)

@Composable
fun defaultEditTextStyle(
    fontSize: TextUnit = AppDimens.Fonts.font20,
    textColor: Color = LocalAppTheme.current.editText.primaryBlackTextColor,
    textAlign: TextAlign = TextAlign.Start
)= TextStyle(
    textColor,
    fontWeight = FontWeight.W100,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign)

@Composable
fun basicEditTextHintStyle(
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textColor: Color = LocalAppTheme.current.editText.hintTextColor,
    textAlign: TextAlign = TextAlign.Start
) = TextStyle(
    textColor,
    fontWeight = FontWeight.W100,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign
)

@Composable
fun defaultEditTextLabelStyle(
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textColor: Color = LocalAppTheme.current.editText.primaryBlackTextColor,
    textAlign: TextAlign = TextAlign.Start
)= TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign)

@Composable
fun basicEditTextSupportStyle(
    fontSize: TextUnit = AppDimens.Fonts.font12,
    textColor: Color = LocalAppTheme.current.editText.redTextColor,
    textAlign: TextAlign = TextAlign.Start
) = TextStyle(
    textColor,
    fontWeight = FontWeight.W100,
    fontSize = fontSize,
    fontFamily = appPrimaryFontFamily(),
    textAlign = textAlign
)

@Composable
fun formTextFieldColor(
    editTextThemeColor : EditTextColors = LocalAppTheme.current.editText,
    focusedLabelColor: Color =  editTextThemeColor.primaryBlackTextColor,
    unfocusedLabelColor: Color = editTextThemeColor.secondaryGreyTextColor,
    disabledLabelColor: Color = editTextThemeColor.secondaryGreyTextColor,
    errorLabelColor: Color = editTextThemeColor.redTextColor,
    focusedIndicatorColor:Color = editTextThemeColor.primaryBlackTextColor,
    unfocusedIndicatorColor:Color = editTextThemeColor.disableColor,
    disabledIndicatorColor:Color = editTextThemeColor.disableColor
) = TextFieldDefaults.colors(
    cursorColor = editTextThemeColor.primaryBlackTextColor,
    errorCursorColor = editTextThemeColor.primaryBlackTextColor,
    focusedIndicatorColor = focusedIndicatorColor,
    unfocusedIndicatorColor = unfocusedIndicatorColor,
    disabledIndicatorColor = disabledIndicatorColor,
    disabledContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,
    errorIndicatorColor = editTextThemeColor.redTextColor,
    focusedLabelColor = focusedLabelColor,
    unfocusedLabelColor = unfocusedLabelColor,
    disabledLabelColor = disabledLabelColor,
    errorLabelColor = errorLabelColor,
    disabledTextColor = editTextThemeColor.secondaryGreyTextColor,
    focusedPlaceholderColor = editTextThemeColor.primaryBlackTextColor,
    unfocusedPlaceholderColor = editTextThemeColor.secondaryGreyTextColor,
    errorPlaceholderColor = editTextThemeColor.secondaryGreyTextColor,
    disabledPlaceholderColor = editTextThemeColor.secondaryGreyTextColor,

    )




