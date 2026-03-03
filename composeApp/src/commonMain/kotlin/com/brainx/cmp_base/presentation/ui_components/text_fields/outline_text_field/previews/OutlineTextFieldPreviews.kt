package com.brainx.cmp_base.presentation.ui_components.text_fields.outline_text_field.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import basecmp.composeapp.generated.resources.search
import basecmp.composeapp.generated.resources.search_movies
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.text_fields.outline_text_field.CustomOutlineTextField
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

private val PreviewPadding = 16.dp

@OptIn(ExperimentalResourceApi::class)
private val labelSearch: StringResource = Res.string.search

@OptIn(ExperimentalResourceApi::class)
private val labelSearchMovies: StringResource = Res.string.search_movies

// region — Basic states

@Preview(showBackground = true, name = "Outline — empty")
@Composable
private fun Preview_Outline_Empty() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "",
                label = labelSearch,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Outline — with text")
@Composable
private fun Preview_Outline_WithText() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "Inception",
                label = labelSearchMovies,
                onValueChange = { }
            )
        }
    }
}

// endregion

// region — Validation & support text

@Preview(showBackground = true, name = "Outline — valid with helper")
@Composable
private fun Preview_Outline_ValidWithHelper() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "Matrix",
                label = labelSearchMovies,
                supportText = CustomTextToDisplay.StringText("Type at least 3 characters"),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Outline — error with support text")
@Composable
private fun Preview_Outline_Error() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "ab",
                label = labelSearchMovies,
                isValid = false,
                supportText = CustomTextToDisplay.StringText("Minimum 3 characters required"),
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Outline — helper (custom color)")
@Composable
private fun Preview_Outline_HelperCustomColor() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — disabled")
@Composable
private fun Preview_Outline_Disabled() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "Disabled value",
                label = labelSearch,
                enabled = false,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Outline — multiline")
@Composable
private fun Preview_Outline_Multiline() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — email")
@Composable
private fun Preview_Outline_Email() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
                text = "user@example.com",
                label = labelSearch,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                onValueChange = { }
            )
        }
    }
}

@Preview(showBackground = true, name = "Outline — password")
@Composable
private fun Preview_Outline_Password() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — dark theme")
@Composable
private fun Preview_Outline_DarkTheme() {
    AppTheme(darkTheme = true, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — leading icon")
@Composable
private fun Preview_Outline_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — trailing icon")
@Composable
private fun Preview_Outline_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

@Preview(showBackground = true, name = "Outline — leading & trailing")
@Composable
private fun Preview_Outline_LeadingTrailing() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(modifier = Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomOutlineTextField(
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

// endregion
