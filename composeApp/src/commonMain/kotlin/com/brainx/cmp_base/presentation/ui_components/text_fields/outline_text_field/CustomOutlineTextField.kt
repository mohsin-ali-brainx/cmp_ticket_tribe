package com.brainx.cmp_base.presentation.ui_components.text_fields.outline_text_field

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.colors.LocalAppTheme
import com.brainx.cmp_base.presentation.ui_components.text.CustomText
import com.brainx.cmp_base.presentation.ui_components.text.CustomTextToDisplay
import com.brainx.cmp_base.presentation.ui_components.text_fields.basicEditTextSupportStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.defaultEditTextLabelStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.defaultEditTextStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.formTextFieldColor
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Reusable outlined text field built with BasicTextField and
 * [OutlinedTextFieldDefaults.DecorationBox]. Supports label, supporting text,
 * error state, and leading/trailing content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlineTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    label: StringResource,
    supportText: CustomTextToDisplay? = null,
    cursorBrush: Color = Color.Black,
    textStyle: TextStyle = defaultEditTextStyle(),
    labelStyle: TextStyle = defaultEditTextLabelStyle(),
    supportTextColor: Color = Color.Red,
    supportTextStyle: TextStyle = basicEditTextSupportStyle(textColor = supportTextColor),
    maxLength: Int? = null,
    singleLine: Boolean = true,
    minLines: Int = ExtConstants.IntegerConstants.ONE,
    maxLines: Int? = null,
    enabled: Boolean = true,
    isValid: Boolean = true,
    textFieldColor: TextFieldColors = formTextFieldColor(),
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    contentPaddingStart: Dp = AppDimens.Padding.padding8,
    contentPaddingEnd: Dp = AppDimens.Padding.padding8,
    contentPaddingTop: Dp = AppDimens.Padding.padding8,
    contentPaddingBottom: Dp = AppDimens.Padding.padding8,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onCursorPositionChange: ((Int) -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    var isTextFieldValid by remember(isValid) { mutableStateOf(isValid) }
    val interactionSource = remember { MutableInteractionSource() }
    val invalidInputErrorColor = Color.Red

    val resolvedColors by remember(isTextFieldValid) {
        derivedStateOf {
            if (isTextFieldValid) mutableStateOf(textFieldColor)
            else mutableStateOf(textFieldColor.copy(
                focusedIndicatorColor = invalidInputErrorColor,
                unfocusedIndicatorColor = invalidInputErrorColor,
                disabledIndicatorColor = invalidInputErrorColor,
                errorIndicatorColor = invalidInputErrorColor
            ))
        }
    }

    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = text,
                selection = if (text.isEmpty()) TextRange.Zero else TextRange(text.length, text.length)
            )
        )
    }
    val textFieldValue = textFieldValueState.copy(text = text)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }

    var lastTextValue by remember(text) { mutableStateOf(text) }

    Column(modifier = modifier) {
        CompositionLocalProvider(
            LocalTextSelectionColors provides TextSelectionColors(
                handleColor = textStyle.color,
                backgroundColor = textStyle.color.copy(alpha = 0.3f)
            )
        ) {
            BasicTextField(
                modifier = textFieldModifier,
                value = textFieldValue,
                enabled = enabled,
                onValueChange = { newValue ->
                    if (maxLength != null && newValue.text.length > maxLength) return@BasicTextField
                    textFieldValueState = newValue
                    val stringChanged = lastTextValue != newValue.text
                    lastTextValue = newValue.text
                    if (stringChanged) onValueChange(newValue.text)
                    onCursorPositionChange?.invoke(newValue.selection.start)
                    isTextFieldValid = true
                },
                singleLine = singleLine,
                textStyle = textStyle,
                cursorBrush = SolidColor(cursorBrush),
                keyboardOptions = KeyboardOptions(
                    capitalization = if (keyboardType == KeyboardType.Password) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = false,
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                minLines = minLines,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                maxLines = if (singleLine) ExtConstants.IntegerConstants.ONE else (maxLines ?: Int.MAX_VALUE),
                decorationBox = { innerTextField ->
                    OutlinedTextFieldDefaults.DecorationBox(
                        value = textFieldValue.text,
                        innerTextField = innerTextField,
                        enabled = enabled,
                        singleLine = singleLine,
                        visualTransformation = visualTransformation,
                        interactionSource = interactionSource,
                        isError = !isTextFieldValid,
                        label = {
                            Text(
                                text = stringResource(label),
                                fontWeight = FontWeight.W400,
                                style = labelStyle
                            )
                        },
                        leadingIcon = leadingContent,
                        trailingIcon = trailingContent,
                        colors = resolvedColors.value,
                        contentPadding = OutlinedTextFieldDefaults.contentPadding(
                            start = contentPaddingStart,
                            top = contentPaddingTop,
                            end = contentPaddingEnd,
                            bottom = contentPaddingBottom
                        ),
                        container = {
                            OutlinedTextFieldDefaults.Container(
                                enabled = enabled,
                                isError = !isTextFieldValid,
                                interactionSource = interactionSource,
                                colors = resolvedColors.value,
                                shape = OutlinedTextFieldDefaults.shape,
                                focusedBorderThickness = AppDimens.EditText.borderWidth,
                                unfocusedBorderThickness = AppDimens.EditText.borderWidth
                            )
                        }
                    )
                }
            )
        }
        if (supportText != null) {
            val showSupport = when (supportText) {
                is CustomTextToDisplay.StringText -> supportText.text.isBlank().not()
                is CustomTextToDisplay.StringResourceText -> stringResource(supportText.text).isBlank().not()
                is CustomTextToDisplay.AnnotatedStringText -> supportText.text.isBlank().not()
            }
            if (showSupport) {
                CustomText(
                    modifier = Modifier.padding(top = AppDimens.Padding.padding8),
                    text = supportText,
                    textStyle = supportTextStyle
                )
            }
        }
    }
}
