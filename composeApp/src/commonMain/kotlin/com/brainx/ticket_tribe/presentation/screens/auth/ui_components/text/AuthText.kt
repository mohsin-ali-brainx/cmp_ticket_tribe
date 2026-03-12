package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthTitleText(modifier: Modifier,text:CustomTextToDisplay){
    val theme = LocalAppTheme.current
    CustomText(
        modifier = modifier,
        text = text,
        fontSize = AppDimens.Fonts.font20,
        fontWeight = FontWeight.W500,
        color = theme.textView.primaryBlackTextColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun AuthDescriptionText(modifier: Modifier,text:CustomTextToDisplay){
    val theme = LocalAppTheme.current
    CustomText(
        modifier = modifier,
        text = text,
        fontSize = AppDimens.Fonts.font14,
        fontWeight = FontWeight.W500,
        color = theme.textView.secondaryGreyTextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MoveToAuth(modifier: Modifier, vararg text: StringResource, onClick: () -> Unit) {
    val theme = LocalAppTheme.current

    val annotatedString = buildAnnotatedString {
        append(stringResource(text[0]))
        withStyle(style = SpanStyle(theme.textView.blueTextColor)) {
            val texts =  text.drop(1)
            texts.forEach {
                append(stringResource(it))
            }
        }
    }
    CustomText(
        modifier = modifier.then(Modifier.fillMaxWidth())
            .clickableSingleWithoutRipple {
                onClick()
            },
        text = CustomTextToDisplay.AnnotatedStringText(text = annotatedString),
        fontSize = AppDimens.Fonts.font14,
        fontWeight = FontWeight.W400,
        color = theme.textView.secondaryGreyTextColor,
        textAlign = TextAlign.Center
    )
}