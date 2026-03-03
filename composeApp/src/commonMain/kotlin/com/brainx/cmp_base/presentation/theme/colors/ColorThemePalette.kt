package com.brainx.cmp_base.presentation.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
interface ButtonColors {
    val primaryColor: Color
    val primaryBorderColor: Color
    val secondaryColor: Color
    val secondaryBorderColor: Color
    val disable: Color
    val primaryBlackTextColor: Color
    val secondaryWhiteTextColor: Color
}

@Immutable
interface TextViewColors {
    val primaryBlackTextColor: Color
    val secondaryGreyTextColor: Color
    val tertiaryWhiteTextColor: Color
    val greenTextColor: Color
    val redTextColor: Color
    val blueTextColor: Color
}

@Immutable
interface EditTextColors {
    val whiteColor: Color
    val primaryBlackTextColor: Color
    val secondaryGreyTextColor: Color
    val tertiaryWhiteTextColor: Color
    val redTextColor: Color
    val greenTextColor: Color
    val hintTextColor: Color
    val disableColor: Color
}

@Immutable
interface DividerColors {
    val dividerColor: Color
    val dividerColor2: Color
    val dividerColor3: Color
    val dividerColor4: Color
    val dividerColor5: Color
    val dividerColor6: Color
}

@Immutable
interface BackgroundColors {
    val whiteColor: Color
    val backgroundColor: Color
    val backgroundColor2: Color
    val backgroundColor3: Color
    val backgroundColor4: Color
    val backgroundColor5: Color
    val backgroundColor6: Color
    val backgroundColor7: Color
    val blueColor: Color
    val lightBlueColor: Color
    val greyGradientColor1: Color
    val greyGradientColor2: Color
    val counterGradientColor: List<Color>
    val bottomSheetColor: Color
    val greenColor: Color
    val onboardingGradient: List<Color>
}

@Immutable
interface IconColors {
    val greyIconColor: Color
    val greyIconColor2: Color
}

@Immutable
interface BorderColors {
    val borderWhiteColor: Color
    val borderColor: Color
    val borderColor2: Color
    val borderColor3: Color
    val borderColor4: Color
}

@Immutable
interface AppColorTheme {
    val button: ButtonColors
    val textView: TextViewColors
    val editText: EditTextColors
    val divider: DividerColors
    val background: BackgroundColors
    val icon: IconColors
    val border: BorderColors
}