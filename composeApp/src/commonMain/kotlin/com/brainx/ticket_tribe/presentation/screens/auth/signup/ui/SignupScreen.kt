package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brainx.ticket_tribe.presentation.navigation.AppRoutes
import com.brainx.ticket_tribe.presentation.navigation.AuthRoutes
import com.brainx.ticket_tribe.presentation.screens.auth.login.ui_intents.LoginUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_events.SignupUiEvents
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_intents.SignupUiIntents
import com.brainx.ticket_tribe.presentation.screens.auth.signup.ui_state.SignupUiState
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.button.SSOButton
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthDescriptionText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthTitleText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.MoveToAuth
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.ConfirmPasswordTextField
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.EmailTextField
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.PasswordTextField
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.PrimaryBlackButton
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.ticket_tribe.presentation.ui_components.text_fields.underline_text_field.CustomBasicUnderlineTextField
import com.brainx.utils_extensions.ToastDurationType
import com.brainx.utils_extensions.ToastManager
import com.brainx.utils_extensions.compose_ui_utils.ConsumeUIEffects
import com.brainx.utils_extensions.compose_ui_utils.modifiers.customNavigationBarsPadding
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.already_have_an_account
import tickettribecmp.composeapp.generated.resources.confirm_password
import tickettribecmp.composeapp.generated.resources.first_name
import tickettribecmp.composeapp.generated.resources.last_name
import tickettribecmp.composeapp.generated.resources.log_in
import tickettribecmp.composeapp.generated.resources.next
import tickettribecmp.composeapp.generated.resources.sign_up
import tickettribecmp.composeapp.generated.resources.welcome_to_ticket_tribe

@Composable
fun SignupScreen(
    dataState: StateFlow<SignupUiState>,
    uiEvents: Flow<SignupUiEvents>,
    onIntent: (SignupUiIntents) -> Unit,
    onNavigate: (AppRoutes, shouldClearBackStack: Boolean) -> Unit,
    ) {

    val state by dataState.collectAsStateWithLifecycle()

    val toastManager by remember { mutableStateOf(ToastManager()) }

    val theme = LocalAppTheme.current

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ConsumeUIEffects(uiEvents){event, scope ->
        when (event) {
            is SignupUiEvents.UIPrompts.ShowToastMessage -> {
                toastManager.showToast(message = event.message, ToastDurationType.SHORT)
            }
            is SignupUiEvents.Navigate.MoveToLogin -> {
                onNavigate(AuthRoutes.Login,true)
            }
            else -> Unit
        }
    }

    SignupScreenContent(state, onIntent = {
        when(it){
            is SignupUiIntents.ButtonIntents.OnLoginButtonIntent, LoginUiIntents.ButtonIntents.OnLoginButtonIntent, LoginUiIntents.ButtonIntents.OnForgotButtonIntent ->{
                keyboardController?.hide()
                focusManager.clearFocus()
            }
            else -> Unit
        }
        onIntent(it)
    })

}

@Composable
private fun SignupScreenContent(
    dataState: SignupUiState,
    onIntent: (SignupUiIntents) -> Unit
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

            val (title, desc, sso, firstName, lastName ,email, password, confirmPassword,loginBtn, nextBtn) = createRefs()
            val centerGuideline = createGuidelineFromStart(0.5f)

            AuthTitleText(
                text = UiText.StringResourceText(text = Res.string.sign_up),
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
                text = UiText.StringResourceText(text = Res.string.welcome_to_ticket_tribe),
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

            val firstNameText = remember(dataState.firstNameText) { dataState.firstNameText }

            CustomBasicUnderlineTextField(
                text = firstNameText,
                modifier = Modifier
                    .onFocusChanged {

                    }
                    .onFocusEvent {

                    }
                    .constrainAs(firstName){
                        top.linkTo(sso.bottom, margin = AppDimens.Padding.padding20)
                        start.linkTo(parent.start)
                        end.linkTo(centerGuideline, margin = AppDimens.Padding.padding8)
                        bottom.linkTo(lastName.bottom)
                        width = Dimension.fillToConstraints
                    },
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnFirstNameTextUpdate(firstName = it))
                },
                singleLine = true,
                label = Res.string.first_name,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Right)
                    }
                )
            )

            val lastNameText = remember(dataState.lastNameText) { dataState.lastNameText }

            CustomBasicUnderlineTextField(
                text = lastNameText,
                modifier = Modifier
                    .onFocusChanged {

                    }
                    .onFocusEvent {

                    }
                    .constrainAs(lastName){
                        top.linkTo(sso.bottom, margin = AppDimens.Padding.padding20)
                        end.linkTo(parent.end)
                        start.linkTo(centerGuideline, margin = AppDimens.Padding.padding8)
                        bottom.linkTo(firstName.bottom)
                        width= Dimension.fillToConstraints
                    },
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnLastNameTextUpdate(lastName = it))
                },
                singleLine = true,
                label = Res.string.last_name,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            val emailText = remember(dataState.emailText) { dataState.emailText }

            EmailTextField(
                modifier = Modifier.fillMaxWidth().constrainAs(email) {
                    top.linkTo(firstName.bottom, margin = AppDimens.Padding.padding20)
                },
                emailText = emailText,
                focusManager = focusManager,
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnEmailTextUpdate(email = it))
                },
            )

            val passwordText = remember(dataState.passwordText) { dataState.passwordText }
            PasswordTextField(
                modifier = Modifier.fillMaxWidth().constrainAs(password) {
                    top.linkTo(email.bottom, margin = AppDimens.Padding.padding20)
                },
                passwordText = passwordText,
                imeAction = ImeAction.Next,
                onKeyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnPasswordTextUpdate(password = it))
                }
            )


            val confirmPasswordText = remember(dataState.confirmPasswordText) { dataState.confirmPasswordText }
            ConfirmPasswordTextField(
                modifier = Modifier.fillMaxWidth().constrainAs(confirmPassword) {
                    top.linkTo(password.bottom, margin = AppDimens.Padding.padding20)
                },
                label = Res.string.confirm_password,
                passwordText = passwordText,
                confirmPasswordText = confirmPasswordText,
                imeAction = ImeAction.Done,
                onKeyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnConfirmPasswordTextUpdate(confirmPassword = it))
                }
            )

            MoveToAuth(
                modifier = Modifier.constrainAs(loginBtn) {
                    bottom.linkTo(nextBtn.top, margin = AppDimens.Padding.padding16)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                },
                text = arrayOf(Res.string.already_have_an_account,Res.string.log_in),
                onClick = {
                    onIntent(SignupUiIntents.ButtonIntents.OnLoginButtonIntent)
                }
            )

            val isFormValid = remember(dataState.isFormButtonValid) { dataState.isFormButtonValid }
            val isLoading = remember(dataState.isSignUpLoading) { dataState.isSignUpLoading }
            PrimaryBlackButton(
                modifier = Modifier.constrainAs(nextBtn) {
                    bottom.linkTo(parent.bottom, margin = AppDimens.Padding.padding8)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                },
                buttonText = UiText.StringResourceText(text = Res.string.next),
                isEnable = isFormValid,
                isLoading = isLoading
            ) {
                onIntent(SignupUiIntents.ButtonIntents.OnSignupButtonIntent)
            }


        }
    }

}


@Preview(showSystemUi = true)
@Composable
private fun SignupScreenPreview(){
    AppTheme{
        SignupScreenContent(dataState = SignupUiState(), onIntent = {})
    }
}