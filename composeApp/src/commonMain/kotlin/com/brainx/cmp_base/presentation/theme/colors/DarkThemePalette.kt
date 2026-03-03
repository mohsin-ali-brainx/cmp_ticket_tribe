package com.brainx.cmp_base.presentation.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// region — Dark palette
@Immutable
private class DarkButtonColors : ButtonColors {
    override val primaryColor = Color(0xFFE0E0E0)
    override val primaryBorderColor = Color(0xFF2B2B2B)
    override val secondaryColor = Color(0xFF2B2B2B)
    override val secondaryBorderColor = Color(0xFFE0E0E0)
    override val disable = Color(0xFF5C5C5C)
    override val primaryBlackTextColor = Color(0xFFE0E0E0)
    override val secondaryWhiteTextColor = Color(0xFF1A1A1A)
}

@Immutable
private class DarkTextViewColors : TextViewColors {
    override val primaryBlackTextColor = Color(0xFFE8E8E8)
    override val secondaryGreyTextColor = Color(0xFFB0B0B0)
    override val tertiaryWhiteTextColor = Color(0xFF1A1A1A)
    override val greenTextColor = Color(0xFF4ADE80)
    override val redTextColor = Color(0xFFFF6B8A)
    override val blueTextColor = Color(0xFF6C9CFB)
}

@Immutable
private class DarkEditTextColors : EditTextColors {
    override val whiteColor = Color(0xFF1A1A1A)
    override val primaryBlackTextColor = Color(0xFFE8E8E8)
    override val secondaryGreyTextColor = Color(0xFFB0B0B0)
    override val tertiaryWhiteTextColor = Color(0xFF1A1A1A)
    override val redTextColor = Color(0xFFFF6B8A)
    override val greenTextColor = Color(0xFF4ADE80)
    override val hintTextColor = Color(0xFF6E6E6E)
    override val disableColor = Color(0xFF5C5C5C)
}

@Immutable
private class DarkDividerColors : DividerColors {
    override val dividerColor = Color(0xFF4A5258)
    override val dividerColor2 = Color(0xFF3A3A3A)
    override val dividerColor3 = Color(0xFF5A5A5A)
    override val dividerColor4 = Color(0xFF404040)
    override val dividerColor5 = Color(0xFF454545)
    override val dividerColor6 = Color(0xFF4A4A4A)
}

@Immutable
private class DarkBackgroundColors : BackgroundColors {
    override val whiteColor = Color(0xFF1A1A1A)
    override val backgroundColor = Color(0xFF0D0D0D)
    override val backgroundColor2 = Color(0xFF2A2A2A)
    override val backgroundColor3 = Color(0xFF252525)
    override val backgroundColor4 = Color(0xFF232323)
    override val backgroundColor5 = Color(0xFF262626)
    override val backgroundColor6 = Color(0xFF3A3A3A)
    override val backgroundColor7 = Color(0xFF2E2E2E)
    override val blueColor = Color(0xFF4A7CFF)
    override val lightBlueColor = Color(0xFF6C9CFB)
    override val greyGradientColor1 = Color(0xFF2A4A8A)
    override val greyGradientColor2 = Color(0xFF1A2A5A)
    override val counterGradientColor = listOf(Color(0xFF1E2530), Color(0xFF252D3A))
    override val bottomSheetColor = Color(0xFF1E2326)
    override val greenColor = Color(0xFF22C55E)
    override val onboardingGradient: List<Color> get() = listOf(greyGradientColor2, greyGradientColor1)
}

@Immutable
private class DarkIconColors : IconColors {
    override val greyIconColor = Color(0xFFB0B0B0)
    override val greyIconColor2 = Color(0xFF8A8A8A)
}

@Immutable
private class DarkBorderColors : BorderColors {
    override val borderWhiteColor = Color(0xFF4A4A4A)
    override val borderColor = Color(0xFFE0E0E0)
    override val borderColor2 = Color(0xFF4A4A4A)
    override val borderColor3 = Color(0xFF484848)
    override val borderColor4 = Color(0xFF454545)
}

@Immutable
class AppDarkThemeColor : AppColorTheme {
    override val button: ButtonColors = DarkButtonColors()
    override val textView: TextViewColors = DarkTextViewColors()
    override val editText: EditTextColors = DarkEditTextColors()
    override val divider: DividerColors = DarkDividerColors()
    override val background: BackgroundColors = DarkBackgroundColors()
    override val icon: IconColors = DarkIconColors()
    override val border: BorderColors = DarkBorderColors()
}

// endregion