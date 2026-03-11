package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.log_in
import tickettribecmp.composeapp.generated.resources.welcome_to_ticket_tribe

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