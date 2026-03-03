package com.brainx.cmp_base.presentation.ui_components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.appPrimaryFontFamily
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface CustomTextToDisplay{
    data class StringResourceText(val text: StringResource):CustomTextToDisplay
    data class StringText(val text:String):CustomTextToDisplay
    data class AnnotatedStringText(val text: AnnotatedString):CustomTextToDisplay
}

@Composable
fun CustomText(
    modifier: Modifier,
    text: CustomTextToDisplay,
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.W400,
    minLines:Int = 1,
    maxLines:Int = Int.MAX_VALUE,
    fontStyle: FontStyle = FontStyle.Normal,
    textStyle: TextStyle?=null,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textDecoration: TextDecoration? = null,
    brush: Brush? = null
){

    val baseStyle = TextStyle(
        fontWeight = fontWeight,
        fontFamily = appPrimaryFontFamily(),
        fontSize = fontSize,
        fontStyle = fontStyle,
        color = color
    )
    val defaultStyle = textStyle ?: if (brush != null) baseStyle.copy(brush = brush) else baseStyle
    when(text){
        is CustomTextToDisplay.StringResourceText->{
            Text(
                modifier =modifier,
                text =  stringResource(text.text),
                style = defaultStyle,
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration,
            )
        }
        is CustomTextToDisplay.AnnotatedStringText->{
            Text(
                modifier =modifier,
                text =  text.text,
                style = defaultStyle,
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration
            )
        }
        is CustomTextToDisplay.StringText->{
            Text(
                modifier =modifier,
                text =  text.text,
                style = defaultStyle,
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration
            )
        }
    }

}
