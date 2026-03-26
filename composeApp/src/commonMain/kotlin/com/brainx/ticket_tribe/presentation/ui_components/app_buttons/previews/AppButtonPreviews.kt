package com.brainx.ticket_tribe.presentation.ui_components.app_buttons.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.PrimaryBlackButton
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.SecondaryWhiteButton
import com.brainx.ticket_tribe.presentation.ui_components.button.CustomButton
import com.brainx.ticket_tribe.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_arrow
import tickettribecmp.composeapp.generated.resources.ic_search
import tickettribecmp.composeapp.generated.resources.search

private val PreviewPadding = 16.dp

@OptIn(ExperimentalResourceApi::class)
private val primaryButtonLabel: StringResource = Res.string.search

// region — PrimaryBlackButton previews

@Preview(showBackground = true, backgroundColor = 0xFF000000 ,name = "PrimaryBlackButton — enabled")
@Composable
private fun Preview_PrimaryBlackButton_Enabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            PrimaryBlackButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "PrimaryBlackButton — disabled")
@Composable
private fun Preview_PrimaryBlackButton_Disabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            PrimaryBlackButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                isEnable = false,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "PrimaryBlackButton — leading icon")
@Composable
private fun Preview_PrimaryBlackButton_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            PrimaryBlackButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "PrimaryBlackButton — trailing icon")
@Composable
private fun Preview_PrimaryBlackButton_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            PrimaryBlackButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "PrimaryBlackButton — border & icons")
@Composable
private fun Preview_PrimaryBlackButton_Border_Icons() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            PrimaryBlackButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

// endregion

// region — SecondaryWhiteButton previews

@Preview(name = "SecondaryWhiteButton — enabled")
@Composable
private fun Preview_SecondaryWhiteButton_Enabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            SecondaryWhiteButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                onClickAction = { }
            )
        }
    }
}

@Preview( name = "SecondaryWhiteButton — disabled")
@Composable
private fun Preview_SecondaryWhiteButton_Disabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            SecondaryWhiteButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                isEnable = false,
                onClickAction = { }
            )
        }
    }
}

@Preview( name = "SecondaryWhiteButton — leading icon")
@Composable
private fun Preview_SecondaryWhiteButton_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            SecondaryWhiteButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

@Preview( name = "SecondaryWhiteButton — trailing icon")
@Composable
private fun Preview_SecondaryWhiteButton_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            SecondaryWhiteButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

@Preview( name = "SecondaryWhiteButton — leading & trailing icons")
@Composable
private fun Preview_SecondaryWhiteButton_LeadingTrailing() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            SecondaryWhiteButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringResourceText(primaryButtonLabel),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

// endregion

// region — CustomButton (list-style row: label start, trailing chevron end)

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    name = "CustomButton — list row (text start, chevron end)"
)
@Composable
private fun Preview_CustomButton_ListRowTextStartTrailingChevron() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        val theme = LocalAppTheme.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = UiText.StringText("Take a new photo"),
                buttonColor = theme.button.secondaryColor,
                textColor = theme.textView.primaryBlackTextColor,
                borderColor = theme.button.secondaryBorderColor,
                borderWidth = AppDimens.Button.borderWidthHalf,
                buttonRadius = AppDimens.Radius.radius16,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W400,
                expandTextBetweenTrailingIcon = true,
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_arrow),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onClickAction = { }
            )
        }
    }
}

// endregion

