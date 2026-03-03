package com.brainx.cmp_base.presentation.ui_components.button.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.button.CustomButton
import com.brainx.cmp_base.presentation.ui_components.button.defaultFullWidthButtonModifier
import com.brainx.cmp_base.presentation.ui_components.button.defaultWrapContentButtonModifier
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource

private val PreviewPadding = 16.dp

// region — Width variants (wrap, full, custom)

@Preview(showBackground = true, name = "CustomButton — wrap content")
@Composable
private fun Preview_CustomButton_WrapContent() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultWrapContentButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomButton — full width")
@Composable
private fun Preview_CustomButton_FullWidth() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomButton — custom width")
@Composable
private fun Preview_CustomButton_CustomWidth() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(AppDimens.Button.defaultButtonHeight),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                onClickAction = { }
            )
        }
    }
}

// endregion

// region — Basic states

@Preview(showBackground = true, name = "CustomButton — default (wrap)")
@Composable
private fun Preview_CustomButton_Default() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultWrapContentButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomButton — disabled")
@Composable
private fun Preview_CustomButton_Disabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultWrapContentButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                isClickable = false,
                onClickAction = { }
            )
        }
    }
}

// endregion

// region — Icons & border

@Preview(showBackground = true, name = "CustomButton — leading icon (wrap)")
@Composable
private fun Preview_CustomButton_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultWrapContentButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
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

@Preview(showBackground = true, name = "CustomButton — trailing icon (wrap)")
@Composable
private fun Preview_CustomButton_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultWrapContentButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
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

@Preview(showBackground = true, name = "CustomButton — leading & trailing (full width)")
@Composable
private fun Preview_CustomButton_LeadingTrailing_FullWidth() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
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

@Preview(showBackground = true, name = "CustomButton — with border (full width)")
@Composable
private fun Preview_CustomButton_WithBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color(0xFFE0E0E0),
                textColor = Color.Black,
                borderColor = Color(0xFF1E88E5),
                borderWidth = 2.dp,
                onClickAction = { }
            )
        }
    }
}

// endregion

// region — Gradient background & border

@Preview(showBackground = true, name = "CustomButton — gradient background (full width)")
@Composable
private fun Preview_CustomButton_GradientBackground() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFBBDEFB),
                        Color(0xFF90CAF9)
                    )
                ),
                textColor = Color.Black,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomButton — gradient border (full width)")
@Composable
private fun Preview_CustomButton_GradientBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Search"),
                buttonColor = Color.Transparent,
                textColor = Color.Black,
                borderBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF64B5F6),
                        Color(0xFF1E88E5)
                    )
                ),
                borderWidth = 2.dp,
                onClickAction = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "CustomButton — gradient background with icons")
@Composable
private fun Preview_CustomButton_GradientBackground_WithIcons() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFBBDEFB),
                        Color(0xFF90CAF9)
                    )
                ),
                textColor = Color.Black,
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

// region — Theming

@Preview(showBackground = true, name = "CustomButton — custom color (full width)")
@Composable
private fun Preview_CustomButton_CustomColor() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomButton(
                modifier = Modifier.defaultFullWidthButtonModifier(),
                buttonText = CustomTextToDisplay.StringText("Play Video"),
                buttonColor = Color(0xFF43A047),
                textColor = Color.White,
                onClickAction = { }
            )
        }
    }
}


// endregion
