package com.brainx.cmp_base.presentation.ui_components.text.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.AppTheme
import com.brainx.cmp_base.presentation.ui_components.text.CustomText
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay

private val PreviewPadding = 16.dp

// region — StringText (basic & alignment)

@Preview(showBackground = true, name = "String — default")
@Composable
private fun Preview_StringDefault() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Default center text"),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — align start")
@Composable
private fun Preview_StringAlignStart() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Text aligned to start"),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — align end")
@Composable
private fun Preview_StringAlignEnd() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Text aligned to end"),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — justify")
@Composable
private fun Preview_StringAlignJustify() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Justified text sample for longer content to show alignment."),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// endregion

// region — StringText (font size, weight, style)


@Preview(showBackground = true, name = "String — fontSize 24")
@Composable
private fun Preview_StringFontSize24() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Font size 24sp"),
                fontSize = AppDimens.Fonts.font24,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Preview(showBackground = true, name = "String — fontStyle Italic")
@Composable
private fun Preview_StringFontStyleItalic() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Italic style text"),
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// endregion

// region — StringText (solid color, decoration, overflow)

@Preview(showBackground = true, name = "String — solid (theme primary)")
@Composable
private fun Preview_StringCustomColor() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Primary-colored text"),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, name = "String — solid red")
@Composable
private fun Preview_StringSolidRed() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Solid red text"),
                color = Color(0xFFE53935)
            )
        }
    }
}


@Preview(showBackground = true, name = "String — underline")
@Composable
private fun Preview_StringUnderline() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Underlined text"),
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — line through")
@Composable
private fun Preview_StringLineThrough() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Strikethrough text"),
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — overflow ellipsis")
@Composable
private fun Preview_StringOverflowEllipsis() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("This is a long sentence that should be truncated with an ellipsis when it does not fit on one line."),
                maxLines = 1,
                textOverflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "String — overflow clip")
@Composable
private fun Preview_StringOverflowClip() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("This long text is clipped with no ellipsis."),
                maxLines = 1,
                textOverflow = TextOverflow.Clip,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// endregion

// region — StringText (gradient Brush)

@Preview(showBackground = true, name = "String — gradient horizontal")
@Composable
private fun Preview_StringGradientHorizontal() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Horizontal gradient text"),
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFE53935), Color(0xFF1E88E5))
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "String — gradient vertical")
@Composable
private fun Preview_StringGradientVertical() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Vertical gradient"),
                fontSize = AppDimens.Fonts.font24,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF7B1FA2), Color(0xFF00BCD4))
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "String — gradient linear (diagonal)")
@Composable
private fun Preview_StringGradientLinear() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Diagonal gradient"),
                fontSize = AppDimens.Fonts.font18,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE53935),
                        Color(0xFFFB8C00),
                        Color(0xFF43A047)
                    ),
                    start = Offset.Zero,
                    end = Offset(1000f, 1000f)
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "String — gradient (3 colors)")
@Composable
private fun Preview_StringGradientThreeColors() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("Rainbow-style gradient"),
                fontSize = AppDimens.Fonts.font24,
                fontWeight = FontWeight.Bold,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE53935),
                        Color(0xFFFB8C00),
                        Color(0xFF43A047),
                        Color(0xFF1E88E5)
                    )
                )
            )
        }
    }
}

// endregion

// region — StringText (multiline)

@Preview(showBackground = true, name = "String — multiline")
@Composable
private fun Preview_StringMultiline() {
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.StringText("First line of text.\nSecond line here.\nThird line for multiline preview."),
                minLines = 2,
                maxLines = 5,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// endregion

// region — AnnotatedStringText

@Preview(showBackground = true, name = "AnnotatedString — bold span")
@Composable
private fun Preview_AnnotatedStringBold() {
    val annotated = buildAnnotatedString {
        append("Normal ")
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append("bold")
        pop()
        append(" and normal again.")
    }
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.AnnotatedStringText(annotated),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "AnnotatedString — colored span")
@Composable
private fun Preview_AnnotatedStringColored() {
    val annotated = buildAnnotatedString {
        append("Black text, ")
        pushStyle(SpanStyle(color = Color(0xFF2196F3)))
        append("blue span")
        pop()
        append(", black again.")
    }
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.AnnotatedStringText(annotated),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, name = "AnnotatedString — mixed styles")
@Composable
private fun Preview_AnnotatedStringMixed() {
    val annotated = buildAnnotatedString {
        append("Normal ")
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
        append("bold primary")
        pop()
        append(" and ")
        pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
        append("italic")
        pop()
        append(".")
    }
    AppTheme(darkTheme = false, dynamicColor = false) {
        Box(Modifier.fillMaxWidth().padding(PreviewPadding)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = CustomTextToDisplay.AnnotatedStringText(annotated),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// endregion

