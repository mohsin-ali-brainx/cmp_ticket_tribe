package com.brainx.ticket_tribe.presentation.screens.auth.ui_components.text_fileds

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.email
import tickettribecmp.composeapp.generated.resources.password
import com.brainx.ticket_tribe.presentation.screens.auth.ui_components.states.FormValidityState
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.ticket_tribe.presentation.ui_components.text_fields.underline_text_field.CustomBasicUnderlineTextField
import com.brainx.ticket_tribe.utils.validators.ConfirmPasswordValidator
import com.brainx.ticket_tribe.utils.validators.EmailValidator
import com.brainx.ticket_tribe.utils.validators.NameValidator
import com.brainx.ticket_tribe.utils.validators.PasswordValidator
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.StringResource
import tickettribecmp.composeapp.generated.resources.ic_hide_password
import tickettribecmp.composeapp.generated.resources.ic_show_password
import tickettribecmp.composeapp.generated.resources.please_enter_valid_name

@Composable
fun EmailTextField(
    modifier: Modifier,
    emailText: String,
    focusManager: FocusManager,
    onValueChange: (String) -> Unit,
) {

    var emailErrorState by remember { mutableStateOf(FormValidityState(true, errorText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY)))}

    CustomBasicUnderlineTextField(
        text = emailText,
        modifier = modifier
            .onFocusChanged {
                if (!it.hasFocus) {
                    emailErrorState = EmailValidator().invoke(email = emailText, ignoreEmpty = true)
                }
            }
            .onFocusEvent {

            },
        onValueChange = {
            emailErrorState = FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            onValueChange(it)
        },
        isValid = emailErrorState.isValid,
        supportText = emailErrorState.errorText,
        singleLine = true,
        label = Res.string.email,
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}

@Composable
fun LoginPasswordTextField(
    modifier: Modifier,
    passwordText: String,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    onDone:()->Unit={},
    onValueChange: (String) -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    CustomBasicUnderlineTextField(
        text = passwordText,
        modifier = modifier.fillMaxWidth()
            .onFocusChanged {

            }
            .onFocusEvent {
            },
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        label = Res.string.password,
        keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password,
        imeAction = ImeAction.Done,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingContent = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.size(AppDimens.Icons.smallIconSize)
            ) {
                Image(
                    painter = painterResource(
                        if (passwordVisible) Res.drawable.ic_hide_password else Res.drawable.ic_show_password
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        keyboardActions = KeyboardActions(
            onNext={
                focusManager.moveFocus(FocusDirection.Down)
            },
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus(force = true)
                onDone()
            }
        )
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier,
    label: StringResource = Res.string.password,
    passwordText: String,
    imeAction: ImeAction=ImeAction.Done,
    onKeyboardActions: KeyboardActions=KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    val theme = LocalAppTheme.current
    var passwordVisible by remember { mutableStateOf(false) }

    var passwordErrorState by remember { mutableStateOf(FormValidityState(true, errorText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY)))}

    CustomBasicUnderlineTextField(
        text = passwordText,
        modifier = modifier.fillMaxWidth()
            .onFocusChanged {
                if (!it.hasFocus) {
                    passwordErrorState = PasswordValidator().invoke(password = passwordText, ignoreEmpty = true)
                }
            }
            .onFocusEvent {
            },
        onValueChange = {
            passwordErrorState = if(it.isBlank()) FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            else PasswordValidator().invoke(password = it, ignoreEmpty = true)
            onValueChange(it)
        },
        isValid = passwordErrorState.isValid,
        showSuccess = !passwordText.isBlank() && passwordErrorState.isValid,
        successIndicatorColor = if (passwordText.isBlank() || !passwordErrorState.isValid) null else theme.editText.greenTextColor,
        supportText = if (passwordText.isBlank()) null else passwordErrorState.errorText,
        supportTextColor = if(passwordErrorState.isValid)  theme.editText.greenTextColor else theme.editText.redTextColor,
        singleLine = true,
        label = label,
        keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password,
        imeAction = imeAction,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingContent = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.size(AppDimens.Icons.smallIconSize)
            ) {
                Image(
                    painter = painterResource(
                        if (passwordVisible) Res.drawable.ic_hide_password else Res.drawable.ic_show_password
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        keyboardActions = onKeyboardActions
    )
}

@Composable
fun ConfirmPasswordTextField(
    modifier: Modifier,
    label: StringResource = Res.string.password,
    confirmPasswordText: String,
    passwordText:String,
    imeAction: ImeAction=ImeAction.Done,
    onKeyboardActions: KeyboardActions=KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    val theme = LocalAppTheme.current
    var passwordVisible by remember { mutableStateOf(false) }

    var passwordErrorState by remember { mutableStateOf(FormValidityState(true, errorText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY)))}

    LaunchedEffect(passwordText, confirmPasswordText) {
        passwordErrorState =
            if (confirmPasswordText.isBlank()) {
                FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            } else {
                ConfirmPasswordValidator().invoke(
                    confirmPassword = confirmPasswordText,
                    password = passwordText,
                    ignoreEmpty = true
                )
            }
    }

    CustomBasicUnderlineTextField(
        text = confirmPasswordText,
        modifier = modifier.fillMaxWidth()
            .onFocusChanged {
                if (!it.hasFocus) {
                    passwordErrorState = ConfirmPasswordValidator().invoke(confirmPassword = confirmPasswordText, password = passwordText, ignoreEmpty = true)
                }
            }
            .onFocusEvent {
            },
        onValueChange = {
            passwordErrorState =
                if (it.isBlank()) FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
                else ConfirmPasswordValidator().invoke(confirmPassword = it, password = passwordText, ignoreEmpty = true)
            onValueChange(it)
        },
        isValid = passwordErrorState.isValid,
        showSuccess = !confirmPasswordText.isBlank() && passwordErrorState.isValid,
        successIndicatorColor = if (confirmPasswordText.isBlank() || !passwordErrorState.isValid) null else theme.editText.greenTextColor,
        supportText = if (confirmPasswordText.isBlank()) null else passwordErrorState.errorText,
        supportTextColor = if(passwordErrorState.isValid)  theme.editText.greenTextColor else theme.editText.redTextColor,
        singleLine = true,
        label = label,
        keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password,
        imeAction = imeAction,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingContent = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.size(AppDimens.Icons.smallIconSize)
            ) {
                Image(
                    painter = painterResource(
                        if (passwordVisible) Res.drawable.ic_hide_password else Res.drawable.ic_show_password
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        keyboardActions = onKeyboardActions
    )
}

@Composable
fun SimpleTextFieldWithErrorState(
    modifier: Modifier,
    text: String,
    label: StringResource,
    imeAction: ImeAction=ImeAction.Next,
    onKeyboardActions: KeyboardActions=KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {
    var errorState by remember { mutableStateOf(FormValidityState(true, errorText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY)))}

    CustomBasicUnderlineTextField(
        text = text,
        modifier = modifier
            .onFocusChanged {
                if (!it.hasFocus) {
                    errorState = NameValidator().invoke(text = text, errorMessage = Res.string.please_enter_valid_name ,ignoreEmpty = true)
                }
            }
            .onFocusEvent {

            },
        onValueChange = {
            errorState = FormValidityState(true, UiText.StringText(text = ExtConstants.StringConstants.EMPTY))
            onValueChange(it)
        },
        isValid = errorState.isValid,
        supportText = errorState.errorText,
        singleLine = true,
        label = label,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        keyboardActions = onKeyboardActions
    )
}

@Composable
fun SimpleTextField(
    modifier: Modifier,
    text: String,
    label: StringResource,
    imeAction: ImeAction=ImeAction.Next,
    onKeyboardActions: KeyboardActions=KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {

    CustomBasicUnderlineTextField(
        text = text,
        modifier = modifier,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        label = label,
        keyboardType = KeyboardType.Text,
        imeAction = imeAction,
        keyboardActions = onKeyboardActions
    )
}


@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun EmailTextFieldPreview(){
    AppTheme { EmailTextField(
        emailText = "john@mailinator.com",
        modifier = Modifier.fillMaxWidth(),
        focusManager =  LocalFocusManager.current,
        onValueChange = {

        }
    )
    }
}


@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun LoginPasswordTextFieldPreview(){
    AppTheme { LoginPasswordTextField(
        passwordText = "password@123",
        modifier = Modifier.fillMaxWidth(),
        focusManager =  LocalFocusManager.current,
        keyboardController = LocalSoftwareKeyboardController.current,
        onDone = {

        },
        onValueChange = {

        }
    )
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun PasswordTextFieldWithErrorPreview(){
    AppTheme { PasswordTextField(
        passwordText = "d",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {

        }
    )
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun PasswordTextFieldPreview(){
    AppTheme { PasswordTextField(
        passwordText = "Password@123",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {

        }
    )
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun ConfirmPasswordTextFieldWithErrorPreview(){
    AppTheme { ConfirmPasswordTextField(
        passwordText = "",
        confirmPasswordText = "d",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {

        }
    )
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFFFFF)
@Composable
private fun ConfirmPasswordTextFieldPreview(){
    AppTheme {  ConfirmPasswordTextField(
        passwordText = "Pass@1234",
        confirmPasswordText = "Pass@1234",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {

        }
    )
    }
}
