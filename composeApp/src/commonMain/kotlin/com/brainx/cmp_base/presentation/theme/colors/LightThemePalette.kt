package com.brainx.cmp_base.presentation.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// region — Light palette (matches original AppThemeColors values)
@Immutable
private class LightButtonColors : ButtonColors {
    override val primaryColor = Color(0xFF2B2B2B)
    override val primaryBorderColor = Color(0xFFFFFFFF)
    override val secondaryColor = Color(0xFFFFFFFF)
    override val secondaryBorderColor = Color(0xFF2B2B2B)
    override val disable = Color(0xFFBBBBBB)
    override val primaryBlackTextColor = Color(0xFF2B2B2B)
    override val secondaryWhiteTextColor = Color(0xFFFFFFFF)
}

@Immutable
private class LightTextViewColors : TextViewColors {
    override val primaryBlackTextColor = Color(0xFF2B2B2B)
    override val secondaryGreyTextColor = Color(0xFF7E7E7E)
    override val tertiaryWhiteTextColor = Color(0xFFFFFFFF)
    override val greenTextColor = Color(0xFF0F9303)
    override val redTextColor = Color(0xFFFF2C5E)
    override val blueTextColor = Color(0xFF004EE8)
}

@Immutable
private class LightEditTextColors : EditTextColors {
    override val whiteColor = Color(0xFFFFFFFF)
    override val primaryBlackTextColor = Color(0xFF2B2B2B)
    override val secondaryGreyTextColor = Color(0xFF7E7E7E)
    override val tertiaryWhiteTextColor = Color(0xFFFFFFFF)
    override val redTextColor = Color(0xFFFF2C5E)
    override val greenTextColor = Color(0xFF0F9303)
    override val hintTextColor = Color(0xFF8C8C8C)
    override val disableColor = Color(0xFFBBBBBB)
}

@Immutable
private class LightDividerColors : DividerColors {
    override val dividerColor = Color(0xFF3A4145)
    override val dividerColor2 = Color(0xFFE8E8E8)
    override val dividerColor3 = Color(0xFFA9A9A9)
    override val dividerColor4 = Color(0xFFE6E6E6)
    override val dividerColor5 = Color(0xFFDBDBDB)
    override val dividerColor6 = Color(0xFFD6D6D6)
}

@Immutable
private class LightBackgroundColors : BackgroundColors {
    override val whiteColor = Color(0xFFFFFFFF)
    override val backgroundColor = Color(0xFF2B2B2B)
    override val backgroundColor2 = Color(0xFFEBEBEB)
    override val backgroundColor3 = Color(0xFFF8F8F8)
    override val backgroundColor4 = Color(0xFFF6F6F6)
    override val backgroundColor5 = Color(0xFFF9F9F9)
    override val backgroundColor6 = Color(0xFFD9D9D9)
    override val backgroundColor7 = Color(0xFFF1F1F1)
    override val blueColor = Color(0xFF004EE8)
    override val lightBlueColor = Color(0xFF6C9CFB)
    override val greyGradientColor1 = Color(0xFF004EE8)
    override val greyGradientColor2 = Color(0xFF002C82)
    override val counterGradientColor = listOf(Color(0xFFFBFCFF), Color(0xFFEFF5FF))
    override val bottomSheetColor = Color(0xFF262C2F)
    override val greenColor = Color(0xFF1BB13F)
    override val onboardingGradient: List<Color> get() = listOf(greyGradientColor2, greyGradientColor1)
}

@Immutable
private class LightIconColors : IconColors {
    override val greyIconColor = Color(0xFF7E7E7E)
    override val greyIconColor2 = Color(0xFF494949)
}

@Immutable
private class LightBorderColors : BorderColors {
    override val borderWhiteColor = Color(0xFFFFFFFF)
    override val borderColor = Color(0xFF2B2B2B)
    override val borderColor2 = Color(0xFFD6D6D6)
    override val borderColor3 = Color(0xFFD8D8D8)
    override val borderColor4 = Color(0xFFD9D9D9)
}

@Immutable
class AppLightThemeColor : AppColorTheme {
    override val button: ButtonColors = LightButtonColors()
    override val textView: TextViewColors = LightTextViewColors()
    override val editText: EditTextColors = LightEditTextColors()
    override val divider: DividerColors = LightDividerColors()
    override val background: BackgroundColors = LightBackgroundColors()
    override val icon: IconColors = LightIconColors()
    override val border: BorderColors = LightBorderColors()
}

// endregion