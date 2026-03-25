package com.brainx.ticket_tribe.presentation.screens.auth.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
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
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthDescriptionText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text.AuthTitleText
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.SimpleNameTextFieldWithErrorState
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.SimpleTextField
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds.SimpleUserNameTextFieldWithErrorState
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.app_buttons.PrimaryBlackButton
import com.brainx.ticket_tribe.presentation.ui_components.button.IconButton
import com.brainx.ticket_tribe.presentation.ui_components.checkbox.CustomCheckbox
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.ToastDurationType
import com.brainx.utils_extensions.ToastManager
import com.brainx.utils_extensions.compose_ui_utils.ConsumeUIEffects
import com.brainx.utils_extensions.compose_ui_utils.modifiers.customNavigationBarsPadding
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import com.brainx.utils_extensions.constants.ExtConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.add_profile_image_optional
import tickettribecmp.composeapp.generated.resources.agree_terms
import tickettribecmp.composeapp.generated.resources.country_code
import tickettribecmp.composeapp.generated.resources.ic_arrow
import tickettribecmp.composeapp.generated.resources.ic_back
import tickettribecmp.composeapp.generated.resources.ic_upload
import tickettribecmp.composeapp.generated.resources.location
import tickettribecmp.composeapp.generated.resources.phone_number
import tickettribecmp.composeapp.generated.resources.sign_up
import tickettribecmp.composeapp.generated.resources.sign_up_heading
import tickettribecmp.composeapp.generated.resources.user_name

@Composable
fun SignupProfileSetupScreen(
    dataState: StateFlow<SignupUiState>,
    uiEvents: Flow<SignupUiEvents>,
    onIntent: (SignupUiIntents) -> Unit,
    onNavigate: (AppRoutes, shouldClearBackStack: Boolean) -> Unit,
    onBack:()-> Unit
){
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

    SignupProfileSetupScreenContent(state,
        onBack=onBack,
        onIntent = {
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
private fun SignupProfileSetupScreenContent(
    dataState: SignupUiState,
    onIntent: (SignupUiIntents) -> Unit,
    onBack:()-> Unit

){

    val theme = LocalAppTheme.current

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isKeyboardVisible by remember { mutableStateOf(false) }

    val keyboardHeight = WindowInsets.ime.getBottom(density = LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        isKeyboardVisible = keyboardHeight > 0
    }

    val userNameText = remember(dataState.userNameText) { dataState.userNameText }
    val countryCodeText = remember(dataState.countryCode) { dataState.countryCode }
    val phoneText = remember(dataState.phoneText) { dataState.phoneText }
    val locationText = remember(dataState.locationText) { dataState.locationText }
    val isTermsChecked = remember(dataState.isTermsChecked) { dataState.isTermsChecked }
    val isFormValid = remember(dataState.isSignupProfileFormButtonValid) { dataState.isSignupProfileFormButtonValid }
    val isLoading = remember(dataState.isSignUpLoading) { dataState.isSignUpLoading }


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
                .verticalScroll(rememberScrollState()),

        ) {
            val (title, desc, back ,profileImage, addProfileTitle, username ,countryCode, phone, location, termCheckbox , signupBtn) = createRefs()
            val centerGuideline = createGuidelineFromStart(0.35f)

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
                text = UiText.StringResourceText(text = Res.string.sign_up_heading),
                modifier = Modifier.constrainAs(desc) {
                    top.linkTo(title.bottom, margin = AppDimens.Padding.padding12)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                }.fillMaxWidth(),
            )

            IconButton(
                modifier = Modifier
                    .size(AppDimens.Button.backIconButton)
                    .constrainAs(back){
                        linkTo(top = title.top,bottom=desc.bottom)
                        start.linkTo(parent.start) }
                    .clickableSingleWithoutRipple{
                        onBack()
                    }
                ,
                icon = Res.drawable.ic_back
            )

            IconButton(
                modifier = Modifier
                    .size(AppDimens.Images.profilePictureSize)
                    .background(color = theme.background.backgroundColor2, shape = CircleShape)
                    .constrainAs(profileImage){
                        top.linkTo(desc.bottom, margin = AppDimens.Padding.padding16)
                        linkTo(
                            start = parent.start,
                            end = parent.end,

                            )
                    },
                icon = Res.drawable.ic_upload
            )


            CustomText(
                modifier = Modifier.constrainAs(addProfileTitle){
                    top.linkTo(profileImage.bottom, margin = AppDimens.Padding.padding16)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                },
                text = UiText.StringResourceText(text = Res.string.add_profile_image_optional),
                fontSize = AppDimens.Fonts.font16,
                fontWeight = FontWeight.W400,
                color = theme.textView.primaryBlackTextColor,
                textAlign = TextAlign.Center
            )



            SimpleUserNameTextFieldWithErrorState(
                    text = userNameText,
                    modifier = Modifier
                        .fillMaxWidth().
                        constrainAs(username){
                        top.linkTo(addProfileTitle.bottom, margin = AppDimens.Padding.padding20)
                    },
                    onValueChange = {
                        onIntent(SignupUiIntents.TextFieldsIntent.OnUserNameTextUpdate(username = it))
                    },
                    label = Res.string.user_name,
                    imeAction = ImeAction.Next,
                    onKeyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
            )


            SimpleNameTextFieldWithErrorState(
                text = countryCodeText,
                modifier = Modifier
                    .constrainAs(countryCode){
                        top.linkTo(username.bottom, margin = AppDimens.Padding.padding20)
                        start.linkTo(parent.start)
                        end.linkTo(centerGuideline, margin = AppDimens.Padding.padding4)
                        bottom.linkTo(phone.bottom)
                        width = Dimension.fillToConstraints
                    },
                onValueChange = {
                },
                label = Res.string.country_code,
                imeAction = ImeAction.Next,
                onKeyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Right)
                    }
                )
            )


            SimpleTextField(
                text = phoneText,
                modifier = Modifier
                    .constrainAs(phone){
                        top.linkTo(username.bottom, margin = AppDimens.Padding.padding20)
                        end.linkTo(parent.end)
                        start.linkTo(centerGuideline, margin = AppDimens.Padding.padding4)
                        bottom.linkTo(countryCode.bottom)
                        width= Dimension.fillToConstraints
                    },
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnLastNameTextUpdate(lastName = it))
                },
                label = Res.string.phone_number,
                imeAction = ImeAction.Next,
                onKeyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )


            SimpleTextField(
                text = locationText,
                modifier = Modifier
                    .fillMaxWidth().
                    constrainAs(location){
                        top.linkTo(phone.bottom, margin = AppDimens.Padding.padding20)
                    }
                    .focusProperties{
                        canFocus=false
                    },
                onValueChange = {
                    onIntent(SignupUiIntents.TextFieldsIntent.OnUserNameTextUpdate(username = it))
                },
                readOnly = true,
                enabled = true,
                label = Res.string.location,
                imeAction = ImeAction.None,
                trailingIcon = Res.drawable.ic_arrow,
            )

            CustomCheckbox(
                checked = isTermsChecked,
                label = UiText.StringResourceText(text = Res.string.agree_terms),
                modifier = Modifier
                    .clickableSingleWithoutRipple{
                        onIntent(SignupUiIntents.CheckBoxIntent.OnTermsCheckboxIntent)
                    }
                    .padding(horizontal = AppDimens.Padding.padding8)
                    .fillMaxWidth()
                    .constrainAs(termCheckbox) {
                        linkTo(
                            top = location.bottom,
                            bottom = signupBtn.top,
                            bias = ExtConstants.FloatConstants.ZERO,
                            topMargin = AppDimens.Padding.padding8,
                        )
                    }


            )


            PrimaryBlackButton(
                modifier = Modifier.constrainAs(signupBtn) {
                    bottom.linkTo(parent.bottom, margin = AppDimens.Padding.padding8)
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        bias = ExtConstants.FloatConstants.ZERO
                    )
                },
                buttonText = UiText.StringResourceText(text = Res.string.sign_up),
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
        SignupProfileSetupScreenContent(dataState = SignupUiState(), onIntent = {}, onBack = {})
    }
}