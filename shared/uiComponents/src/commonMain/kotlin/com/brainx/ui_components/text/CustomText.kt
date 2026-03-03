package com.brainx.ui_components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.stringResource

@Composable
fun CustomText(
    modifier: Modifier,
    text: DisplayText,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    textAlign: TextAlign = TextAlign.Center,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontFamily: FontFamily? = null,
    fontWeight: FontWeight = FontWeight.W400,
    minLines:Int = 1,
    maxLines:Int = Int.MAX_VALUE,
    fontStyle: FontStyle = FontStyle.Normal,
    textStyle: TextStyle?=null,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textDecoration: TextDecoration? = null
){
    when(text){
        is DisplayText.StringResourceText->{
            Text(
                modifier =modifier,
                text =  stringResource(text.text),
                style = textStyle ?: TextStyle(
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    color = color
                ),
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration,
            )
        }
        is DisplayText.AnnotatedStringText->{
            Text(
                modifier =modifier,
                text =  text.text,
                style = textStyle ?: TextStyle(
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    color = color
                ),
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration
            )
        }
        is DisplayText.StringText->{
            Text(
                modifier =modifier,
                text =  text.text,
                style = textStyle ?: TextStyle(
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    color = color
                ),
                textAlign = textAlign,
                minLines = minLines,
                maxLines = maxLines,
                overflow = textOverflow,
                textDecoration = textDecoration
            )
        }
    }

}