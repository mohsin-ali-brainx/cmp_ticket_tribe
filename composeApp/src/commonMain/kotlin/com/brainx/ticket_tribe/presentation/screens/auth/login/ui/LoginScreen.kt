package com.brainx.ticket_tribe.presentation.screens.auth.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_events.LoginUiEvents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_intents.LoginUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_state.LoginUiState
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.button.SSOButton
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthDescriptionText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthTitleText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.MoveToAuth
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.EmailTextField
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.LoginPasswordTextField
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.PlatformColors
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.PrimaryBlackButton
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.utils_extensions.ToastDurationType
import com.brainx.utils_extensions.ToastManager
import com.brainx.utils_extensions.compose_ui_utils.ConsumeUIEffects
import com.brainx.utils_extensions.compose_ui_utils.modifiers.customNavigationBarsPadding
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.dont_have_account
import tickettribecmp.composeapp.generated.resources.forgot_password
import tickettribecmp.composeapp.generated.resources.log_in
import tickettribecmp.composeapp.generated.resources.sign_up
import tickettribecmp.composeapp.generated.resources.welcome_to_ticket_tribe

@Composable
fun LoginScreen(
    dataState: StateFlow<LoginUiState>,
    uiEvents: Flow<LoginUiEvents>,
    onIntent: (LoginUiIntents) -> Unit,
) {
    val state by dataState.collectAsStateWithLifecycle()
//    val toastManager = rememberToastManager()

    val toastManager by remember { mutableStateOf(ToastManager()) }

    val theme = LocalAppTheme.current

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ConsumeUIEffects(uiEvents){event, scope ->
        when (event) {
            is LoginUiEvents.UIPrompts.ShowToastMessage -> {
                toastManager.showToast(message = event.message, ToastDurationType.SHORT)
            }
            else -> Unit
        }
    }

    LoginScreenContent(state, onIntent = {
        when(it){
            is LoginUiIntents.ButtonIntents.OnSignUpButtonIntent, LoginUiIntents.ButtonIntents.OnLoginButtonIntent, LoginUiIntents.ButtonIntents.OnForgotButtonIntent ->{
                keyboardController?.hide()
                focusManager.clearFocus()
            }
            else -> Unit
        }
        onIntent(it)
    })
}

@Composable
fun LoginScreenContent(
    dataState: LoginUiState,
    onIntent: (LoginUiIntents) -> Unit
){
    val theme = LocalAppTheme.current

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isKeyboardVisible by remember { mutableStateOf(false) }

    val keyboardHeight = WindowInsets.ime.getBottom(density = LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        isKeyboardVisible = keyboardHeight > 0
    }

    Scaffold(
        modifier = Modifier
            .background(theme.background.whiteColor)
            .fillMaxSize()
            .customNavigationBarsPadding()
            .statusBarsPadding()
            .imePadding()
            .clickableSingleWithoutRipple {
                focusManager.clearFocus(force = true)
                keyboardController?.hide()
            }
    ) { paddingValues ->
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .background(theme.background.whiteColor)
                .padding(horizontal = AppDimens.Padding.padding16)
                .verticalScroll(rememberScrollState())
        ) {
            val (title, desc, sso, email, password, forgotPassword, signUp, loginBtn) = createRefs()

            AuthTitleText(
                text = CustomTextToDisplay.StringResourceText(text = Res.string.log_in),
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = AppDimens.Padding.padding16)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                }.fillMaxWidth(),
            )

            AuthDescriptionText(
                text = CustomTextToDisplay.StringResourceText(text = Res.string.welcome_to_ticket_tribe),
                modifier = Modifier.constrainAs(desc) {
                    top.linkTo(title.bottom, margin = AppDimens.Padding.padding12)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                }.fillMaxWidth(),
            )

            SSOButton(
                modifier = Modifier.fillMaxWidth().constrainAs(sso) {
                    top.linkTo(desc.bottom, margin = AppDimens.Padding.padding32)
                },
                onGoogleClickAction = {},
                onAppleClickAction = {}
            )

            val emailText = remember(dataState.emailText) { dataState.emailText }

            EmailTextField(
                modifier = Modifier.fillMaxWidth().constrainAs(email) {
                    top.linkTo(sso.bottom, margin = AppDimens.Padding.padding42)
                },
                emailText = emailText,
                focusManager = focusManager,
                onValueChange = {
                    onIntent(LoginUiIntents.TextFieldsIntent.OnEmailTextUpdate(email = it))
                },
            )

            val passwordText = remember(dataState.passwordText) { dataState.passwordText }

            LoginPasswordTextField(
                modifier = Modifier.fillMaxWidth().constrainAs(password) {
                    top.linkTo(email.bottom, margin = AppDimens.Padding.padding20)
                },
                passwordText = passwordText,
                focusManager = focusManager,
                keyboardController = keyboardController,
                onDone = {
                    onIntent(LoginUiIntents.ButtonIntents.OnLoginButtonIntent)
                },
                onValueChange = {
                    onIntent(LoginUiIntents.TextFieldsIntent.OnPasswordTextUpdate(password = it))
                }
            )

            CustomText(
                modifier = Modifier.constrainAs(forgotPassword) {
                    end.linkTo(password.end)
                    linkTo(
                        top = password.bottom,
                        bottom = signUp.top,
                        bias = ExtConstants.FloatConstants.ZERO,
                        topMargin = AppDimens.Padding.padding16,
                        bottomMargin = AppDimens.Padding.padding12
                    )
                }.wrapContentSize(),
                text = CustomTextToDisplay.StringResourceText(text = Res.string.forgot_password),
                fontSize = AppDimens.Fonts.font16,
                fontWeight = FontWeight.W400,
                color = theme.textView.blueTextColor,
                textAlign = TextAlign.Right
            )

            MoveToAuth(
                modifier = Modifier.constrainAs(signUp) {
                    bottom.linkTo(loginBtn.top, margin = AppDimens.Padding.padding16)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                },
                text = arrayOf(Res.string.dont_have_account,Res.string.sign_up),
                onClick = {

                }
            )

            val isFormValid = remember(dataState.isFormButtonValid) { dataState.isFormButtonValid }
            val isLoading = remember(dataState.isLoginLoading) { dataState.isLoginLoading }
            PrimaryBlackButton(
                modifier = Modifier.constrainAs(loginBtn) {
                    bottom.linkTo(parent.bottom, margin = AppDimens.Padding.padding8)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                },
                buttonText = CustomTextToDisplay.StringResourceText(text = Res.string.log_in),
                isEnable = isFormValid,
                isLoading = isLoading
            ) {
                onIntent(LoginUiIntents.ButtonIntents.OnLoginButtonIntent)
            }

        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    AppTheme{
        LoginScreenContent(dataState = LoginUiState(), onIntent = {})
    }
}