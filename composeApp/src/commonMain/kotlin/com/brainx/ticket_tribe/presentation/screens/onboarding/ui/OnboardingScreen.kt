package com.brainx.ticket_tribe.presentation.screens.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.ticket_tribe.presentation.navigation.AppRoutes
import com.brainx.ticket_tribe.presentation.navigation.AuthRoutes
import com.brainx.ticket_tribe.presentation.screens.onboarding.ui_events.OnboardingUiEvents
import com.brainx.ticket_tribe.presentation.screens.onboarding.ui_intents.OnboardingUiIntents
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.PrimaryBlackButton
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.SecondaryWhiteButton
import com.brainx.ticket_tribe.presentation.ui_components.image.modifiers.onboardingLogoModifier
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.compose_ui_utils.ConsumeUIEffects
import com.brainx.utils_extensions.compose_ui_utils.modifiers.customNavigationBarsPadding
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_logo
import tickettribecmp.composeapp.generated.resources.iv_onboarding
import tickettribecmp.composeapp.generated.resources.log_in
import tickettribecmp.composeapp.generated.resources.onboarding_text
import tickettribecmp.composeapp.generated.resources.sign_up


@Composable
fun OnboardingScreen(
    uiEvents: Flow<OnboardingUiEvents>,
    onIntent: (OnboardingUiIntents) -> Unit,
    onNavigate: (AppRoutes, shouldClearBackStack: Boolean) -> Unit,
    onNavigateSequentially: (firstRoute:AppRoutes, secondRoute: AppRoutes ,shouldClearBackStack: Boolean) -> Unit,
    onBackPress: () -> Unit
) {
    ConsumeUIEffects(uiEvents){event,scope->
        when(event){
            is OnboardingUiEvents.Navigate.MoveToLogin->{
                onNavigate(AppRoutes.Auth, true)
            }
            is OnboardingUiEvents.Navigate.MoveToSignUp->{
                onNavigateSequentially(AppRoutes.Auth, AuthRoutes.SignUp,true)
            }
        }
    }
    OnboardingScreenContent(onIntent = onIntent)
}

@Composable
private fun OnboardingScreenContent(
    onIntent: (OnboardingUiIntents) -> Unit,
){

    val localTheme = LocalAppTheme.current

    Scaffold(
        contentWindowInsets = WindowInsets(
            left= ExtConstants.IntegerConstants.ZERO,
            top=ExtConstants.IntegerConstants.ZERO,
            right=ExtConstants.IntegerConstants.ZERO,
            bottom=ExtConstants.IntegerConstants.ZERO),
        modifier = Modifier
            .background(localTheme.background.greyGradientColor2)
            .fillMaxSize()
    ) {
        Background(
            onIntent = onIntent
        )
    }
}

@Composable
private fun Background(onIntent: (OnboardingUiIntents) -> Unit){

    val localTheme = LocalAppTheme.current

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(localTheme.background.onboardingGradient),
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop ,
            painter = painterResource(Res.drawable.iv_onboarding),
            contentDescription = ExtConstants.StringConstants.EMPTY
        )
        OnboardingContent(
            onIntent = onIntent
        )
    }
}

@Composable
private fun OnboardingContent(
    onIntent: (OnboardingUiIntents) -> Unit,
){
    val localTheme = LocalAppTheme.current

    Column(
        modifier = Modifier.fillMaxSize().customNavigationBarsPadding(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = onboardingLogoModifier(),
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = ExtConstants.StringConstants.EMPTY
        )

        CustomText(
            modifier = Modifier.padding(horizontal =  AppDimens.Padding.padding36, vertical = AppDimens.Padding.padding20).fillMaxWidth(),
            text = CustomTextToDisplay.StringResourceText(text = Res.string.onboarding_text),
            color = localTheme.textView.tertiaryWhiteTextColor,
            fontSize = AppDimens.Fonts.font18,
            fontWeight = FontWeight.W200
        )

        val buttonModifier = Modifier.padding(horizontal = AppDimens.Padding.padding16)
        PrimaryBlackButton(
            modifier = buttonModifier,
            buttonText = CustomTextToDisplay.StringResourceText(Res.string.log_in),
        ){
            onIntent(OnboardingUiIntents.ButtonIntents.OnLogInButtonIntent)
        }
        SecondaryWhiteButton(
            modifier = buttonModifier.padding(vertical = AppDimens.Padding.padding12),
            buttonText =CustomTextToDisplay.StringResourceText(Res.string.sign_up)){
            onIntent(OnboardingUiIntents.ButtonIntents.OnSignUpButtonIntent)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewOnboarding(){
    AppTheme {
        OnboardingScreenContent(){

        }
    }
}