package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.ticket_tribe.getPlatform
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.SecondaryWhiteButton
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_apple
import tickettribecmp.composeapp.generated.resources.ic_google
import tickettribecmp.composeapp.generated.resources.or
import tickettribecmp.composeapp.generated.resources.sign_in_with_apple
import tickettribecmp.composeapp.generated.resources.sign_in_with_google

@Composable
fun SSOButton(
    modifier: Modifier,
    onGoogleClickAction: () -> Unit,
    onAppleClickAction: () -> Unit
){
    Column(modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SecondaryWhiteButton(
            modifier = modifier.fillMaxWidth(),
            buttonText = UiText.StringResourceText(text = Res.string.sign_in_with_google),
            leadingIcon = {
                Image(
                    modifier = Modifier.wrapContentSize().padding(horizontal = AppDimens.Padding.textPadding4),
                    painter = painterResource(Res.drawable.ic_google), contentDescription = ExtConstants.StringConstants.EMPTY)
            },
            onClickAction = onGoogleClickAction,
        )
        if (getPlatform().isIOS){
            SecondaryWhiteButton(
                modifier = modifier.padding(top = AppDimens.Padding.padding8).fillMaxWidth(),
                buttonText = UiText.StringResourceText(text = Res.string.sign_in_with_apple),
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(AppDimens.Icons.mediumIconSize).padding(horizontal = AppDimens.Padding.textPadding4),
                        painter = painterResource(Res.drawable.ic_apple), contentDescription = ExtConstants.StringConstants.EMPTY)
                },
                onClickAction = onAppleClickAction,
            )
        }

        CustomText(modifier=Modifier.padding(top = AppDimens.Padding.padding24),
            text = UiText.StringResourceText(Res.string.or),
            fontSize = AppDimens.Fonts.font12,
            fontWeight = FontWeight.W400
        )

    }
}

@Preview
@Composable
private fun SSOButtonPreview(){
    AppTheme {
        SSOButton(modifier = Modifier.fillMaxSize().wrapContentHeight(), onAppleClickAction = {}, onGoogleClickAction = {})
    }
}