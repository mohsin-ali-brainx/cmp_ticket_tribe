package com.brainx.cmp_base.presentation.ui_components.text_fields.underline_text_field.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import basecmp.composeapp.generated.resources.search
import basecmp.composeapp.generated.resources.search_movies
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.text_fields.underline_text_field.CustomBasicUnderlineTextField
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

private val PreviewPadding = 16.dp

// region — helpers

@OptIn(ExperimentalResourceApi::class)
private val labelSearch: StringResource = Res.string.search

@OptIn(ExperimentalResourceApi::class)
private val labelSearchMovies: StringResource = Res.string.search_movies

// endregion

// region — Basic states

@Preview(showBackground = true, name = "Underline — empty")
@Composable
private fun Preview_Underline_Empty() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearch,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — with text")
@Composable
private fun Preview_Underline_WithText() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "Inception",
                label = labelSearchMovies,
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Validation & support text

@Preview(showBackground = true, name = "Underline — valid with helper")
@Composable
private fun Preview_Underline_ValidWithHelper() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "Matrix",
                label = labelSearchMovies,
                supportText = CustomTextToDisplay.StringText("Type at least 3 characters"),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — error with support text")
@Composable
private fun Preview_Underline_Error() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "ab",
                label = labelSearchMovies,
                isValid = false,
                supportText = CustomTextToDisplay.StringText("Minimum 3 characters required"),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — helper (custom color)")
@Composable
private fun Preview_Underline_HelperCustomColor() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "Valid value",
                label = labelSearchMovies,
                supportText = CustomTextToDisplay.StringText("Looks good!"),
                supportTextColor = Color(0xFF4CAF50),
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Disabled, single-line & multi-line

@Preview(showBackground = true, name = "Underline — disabled")
@Composable
private fun Preview_Underline_Disabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "Disabled value",
                label = labelSearch,
                enabled = false,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — multiline")
@Composable
private fun Preview_Underline_Multiline() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "First line\nSecond line\nThird line",
                label = labelSearchMovies,
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Keyboard types & IME

@Preview(showBackground = true, name = "Underline — email")
@Composable
private fun Preview_Underline_Email() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "user@example.com",
                label = labelSearch,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — password")
@Composable
private fun Preview_Underline_Password() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "password",
                label = labelSearch,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Dark theme

@Preview(showBackground = true, name = "Underline — dark theme")
@Composable
private fun Preview_Underline_DarkTheme() {
    AppTheme(darkTheme = true, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                supportText = CustomTextToDisplay.StringText("Helper text in dark theme"),
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Leading / trailing content & styling

@Preview(showBackground = true, name = "Underline — leading icon")
@Composable
private fun Preview_Underline_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearch,
                leadingContent = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — trailing icon")
@Composable
private fun Preview_Underline_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "With trailing",
                label = labelSearchMovies,
                trailingContent = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — leading & trailing")
@Composable
private fun Preview_Underline_LeadingTrailing() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                leadingContent = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingContent = {
                    Image(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — solid background")
@Composable
private fun Preview_Underline_SolidBackground() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                backgroundColor = Color(0xFFF5F5F5),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — background + border")
@Composable
private fun Preview_Underline_BackgroundBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                backgroundColor = Color(0xFFE3F2FD),
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — gradient background")
@Composable
private fun Preview_Underline_GradientBackground() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                backgroundBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFBBDEFB),
                        Color(0xFF90CAF9)
                    )
                ),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Underline — gradient border")
@Composable
private fun Preview_Underline_GradientBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PreviewPadding)
        ) {
            CustomBasicUnderlineTextField(
                text = "",
                label = labelSearchMovies,
                backgroundColor = Color.White,
                borderWidth = 2.dp,
                borderBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFBBDEFB),
                        Color(0xFF90CAF9),
                        Color(0xFF64B5F6)
                    )
                ),
                onValueChange = { }
            )
        }
    }
}

// endregion
