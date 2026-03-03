package com.brainx.cmp_base.presentation.ui_components.text_fields.basic_text_field.previews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import basecmp.composeapp.generated.resources.Res
import basecmp.composeapp.generated.resources.ic_search
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.text_fields.basic_text_field.CustomTextField
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource

private val PreviewPadding = 16.dp

// region — States & icons (one preview per variant)

@Preview(showBackground = true, name = "Empty")
@Composable
private fun Preview_Empty() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(text = "", placeHolderText = "Type here…", onValueChange = { })
        }
    }
}

@Preview(showBackground = true, name = "With text")
@Composable
private fun Preview_WithText() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(text = "Some content", placeHolderText = "Placeholder", onValueChange = { })
        }
    }
}

@Preview(showBackground = true, name = "Leading icon")
@Composable
private fun Preview_LeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                text = "",
                placeHolderText = "Search…",
                onValueChange = { },
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Trailing icon")
@Composable
private fun Preview_TrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                text = "With trailing",
                placeHolderText = "Placeholder",
                onValueChange = { },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Both icons")
@Composable
private fun Preview_BothIcons() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                text = "",
                placeHolderText = "Search movies…",
                onValueChange = { },
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

// endregion

// region — Background, border, shapes (one preview per variant)

@Preview(showBackground = true, name = "Background only")
@Composable
private fun Preview_BackgroundOnly() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Background only…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius)
            )
        }
    }
}

@Preview(showBackground = true, name = "Background + leading icon")
@Composable
private fun Preview_BackgroundLeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Background only…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Background + trailing icon")
@Composable
private fun Preview_BackgroundTrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Background only…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Border only")
@Composable
private fun Preview_BorderOnly() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "Bordered",
                placeHolderText = "Border only…",
                onValueChange = { },
                shape = RoundedCornerShape(AppDimens.ShapeDimens.defaultEditTextCornerRadius),
                borderColor = Color(0xFF2196F3),
                borderWidth = 2.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "Background + border")
@Composable
private fun Preview_BackgroundAndBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Background + border…",
                onValueChange = { },
                backgroundColor = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp),
                borderColor = Color(0xFF9E9E9E),
                borderWidth = 1.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "Rounded (large)")
@Composable
private fun Preview_RoundedLarge() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Large rounded corners…",
                onValueChange = { },
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Cut corners")
@Composable
private fun Preview_CutCorners() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Cut corners…",
                onValueChange = { },
                backgroundColor = Color(0xFFFFF3E0),
                shape = CutCornerShape(12.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Pill shape")
@Composable
private fun Preview_PillShape() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                text = "",
                placeHolderText = "Pill shape…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8F5E9),
                shape = RoundedCornerShape(28.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Rectangle (sharp)")
@Composable
private fun Preview_RectangleSharp() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Sharp rectangle…",
                onValueChange = { },
                backgroundColor = Color(0xFFECEFF1),
                shape = RoundedCornerShape(0.dp),
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp
            )
        }
    }
}

// endregion

// region — Multiline (one preview per variant)

@Preview(showBackground = true, name = "Multiline + background")
@Composable
private fun Preview_MultilineBackground() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                text = "First line.\nSecond line.\nThird line.",
                placeHolderText = "Multiline with background…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline + border")
@Composable
private fun Preview_MultilineBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                text = "",
                placeHolderText = "Multiline with border…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { },
                shape = RoundedCornerShape(12.dp),
                borderColor = Color(0xFF2196F3),
                borderWidth = 2.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline + background + border")
@Composable
private fun Preview_MultilineBackgroundBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                text = "Line one.\nLine two.\nLine three.",
                placeHolderText = "Background + border…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { },
                backgroundColor = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(12.dp),
                borderColor = Color(0xFF9E9E9E),
                borderWidth = 1.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline + rounded (large)")
@Composable
private fun Preview_MultilineRoundedLarge() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(110.dp),
                text = "",
                placeHolderText = "Large rounded multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 6,
                onValueChange = { },
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline + cut corners")
@Composable
private fun Preview_MultilineCutCorners() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                text = "First.\nSecond.\nThird.",
                placeHolderText = "Cut corner multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { },
                backgroundColor = Color(0xFFFFF3E0),
                shape = CutCornerShape(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Multiline + rectangle + border")
@Composable
private fun Preview_MultilineRectangleBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                text = "",
                placeHolderText = "Sharp rectangle multiline…",
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                onValueChange = { },
                backgroundColor = Color(0xFFECEFF1),
                shape = RoundedCornerShape(0.dp),
                borderColor = Color(0xFF90A4AE),
                borderWidth = 1.dp
            )
        }
    }
}

// endregion

// region — Content padding (one preview per variant)

@Preview(showBackground = true, name = "Content padding — default")
@Composable
private fun Preview_ContentPaddingDefault() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Default padding (8.dp)…",
                onValueChange = { },
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Content padding — large")
@Composable
private fun Preview_ContentPaddingLarge() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Large padding (16.dp)…",
                onValueChange = { },
                backgroundColor = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(24.dp),
                contentPaddingStart = 16.dp,
                contentPaddingEnd = 16.dp,
                contentPaddingTop = 16.dp,
                contentPaddingBottom = 16.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "Content padding — leading icon")
@Composable
private fun Preview_ContentPaddingLeadingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Search…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, name = "Content padding — trailing icon")
@Composable
private fun Preview_ContentPaddingTrailingIcon() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "With trailing icon…",
                onValueChange = { },
                backgroundColor = Color(0xFFE8E8E8),
                shape = RoundedCornerShape(12.dp),
                contentPaddingStart = 12.dp,
                contentPaddingEnd = 12.dp,
                contentPaddingTop = 12.dp,
                contentPaddingBottom = 12.dp,
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

// endregion

// region — Gradient background & border

@Preview(showBackground = true, name = "Gradient background — horizontal")
@Composable
private fun Preview_GradientBackgroundHorizontal() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Horizontal gradient…",
                onValueChange = { },
                backgroundBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD), // light blue
                        Color(0xFFBBDEFB), // soft blue
                        Color(0xFF90CAF9)  // medium soft blue
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Gradient background — vertical")
@Composable
private fun Preview_GradientBackgroundVertical() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Vertical gradient…",
                onValueChange = { },
                backgroundBrush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD), // light blue
                        Color(0xFF90CAF9)  // medium soft blue
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Gradient border")
@Composable
private fun Preview_GradientBorder() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Gradient border…",
                onValueChange = { },
                backgroundColor = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(24.dp),
                borderWidth = 2.dp,
                borderBrush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFBBDEFB), // soft blue
                        Color(0xFF90CAF9), // medium soft blue
                        Color(0xFF64B5F6)  // slightly stronger but still soft
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Gradient background + icons")
@Composable
private fun Preview_GradientBackgroundWithIcons() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "",
                placeHolderText = "Search with gradient…",
                onValueChange = { },
                backgroundBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD), // light blue
                        Color(0xFFBBDEFB), // soft blue
                        Color(0xFF90CAF9)  // medium soft blue
                    ),
                    start = Offset.Zero,
                    end = Offset(1000f, 1000f)
                ),
                shape = RoundedCornerShape(28.dp),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = AppDimens.Padding.padding4),
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }
            )
        }
    }
}

// endregion
