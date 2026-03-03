package com.brainx.cmp_base.presentation.ui_components.text_fields.underline_text_field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
import com.brainx.cmp_base.presentation.ui_components.text_fields.basicEditTextHintStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.basicEditTextSupportStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.defaultEditTextLabelStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.defaultEditTextStyle
import com.brainx.cmp_base.presentation.ui_components.text_fields.formTextFieldColor
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 *
 * Using Basic TextField you can customize any type of EditText like Underline, Outline etc.
 * This is done via decoration box inside Basic TextField and use respective decoration box of respective EditText Type
 * Like it is used here
 * TextFieldDefaults.DecorationBox()
 *
 * for outline there is  OutlinedTextFieldDefaults.DecorationBox()
 *
 * **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBasicUnderlineTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    label:StringResource,
    supportText: CustomTextToDisplay?=null,
    cursorBrush: Color = Color.Black,
    textStyle: TextStyle = defaultEditTextStyle(),
    labelStyle: TextStyle = defaultEditTextLabelStyle(),
    supportTextColor: Color = Color.Red,
    supportTextStyle: TextStyle = basicEditTextSupportStyle(textColor = supportTextColor),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int= ExtConstants.IntegerConstants.ONE,
    maxLines:Int?=null,
    enabled:Boolean=true,
    isValid:Boolean=true,
    textFieldColor: TextFieldColors= formTextFieldColor(),
    leadingContent: (@Composable (() -> Unit))? = null,
    trailingContent: (@Composable (() -> Unit))? = null,
    backgroundColor: Color? = null,
    backgroundBrush: Brush? = null,
    borderColor: Color? = null,
    borderBrush: Brush? = null,
    borderWidth: Dp? = null,
    contentPaddingStart: Dp = AppDimens.Padding.padding8,
    contentPaddingEnd: Dp = AppDimens.Padding.padding8,
    contentPaddingTop: Dp = AppDimens.Padding.padding8,
    contentPaddingBottom: Dp = AppDimens.Padding.padding8,
    visualTransformation: VisualTransformation=VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onCursorPositionChange: ((Int) -> Unit)?=null,
    onValueChange: (String) -> Unit,
){
    var isTextFieldValid by remember(isValid) {
        mutableStateOf(isValid)
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val invalidInputErrorColor = Color.Red

    val textFieldColors by remember(isTextFieldValid) {
        derivedStateOf {
            if (isTextFieldValid)
                mutableStateOf(textFieldColor)
            else
                mutableStateOf(textFieldColor.copy(
                    focusedIndicatorColor = invalidInputErrorColor,
                    unfocusedIndicatorColor = invalidInputErrorColor,
                    disabledIndicatorColor = invalidInputErrorColor,
                    errorIndicatorColor = invalidInputErrorColor
                ))
        }

    }

    // Holds the latest internal TextFieldValue state. We need to keep it to have the correct value
    // of the composition.
    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = text, selection = when {
                    text.isEmpty() -> TextRange.Zero
                    else -> TextRange(text.length, text.length)
                }
            )
        )
    }


    // Holds the latest TextFieldValue that BasicTextField was recomposed with. We couldn't simply
    // pass `TextFieldValue(text = value)` to the CoreTextField because we need to preserve the
    // composition.
    val textFieldValue = textFieldValueState.copy(text = text)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    // Last String value that either text field was recomposed with or updated in the onValueChange
    // callback. We keep track of it to prevent calling onValueChange(String) for same String when
    // CoreTextField's onValueChange is called multiple times without recomposition in between.
    var lastTextValue by remember(text) { mutableStateOf(text) }




    if (backgroundColor != null && backgroundBrush != null) {
        throw IllegalArgumentException("CustomBasicUnderlineTextField: 'backgroundColor' and 'backgroundBrush' cannot be used together. Use only one.")
    }
    if (borderColor != null && borderBrush != null) {
        throw IllegalArgumentException("CustomBasicUnderlineTextField: 'borderColor' and 'borderBrush' cannot be used together. Use only one.")
    }

    val hasBorder = borderWidth != null && borderWidth > 0.dp && (borderColor != null || borderBrush != null)
    val hasStyling = backgroundColor != null || backgroundBrush != null || hasBorder
    val containerShape: Shape? = if (hasStyling) TextFieldDefaults.shape else null

    val containerModifier = modifier
        .then(
            when {
                backgroundBrush != null && containerShape != null ->
                    Modifier.background(backgroundBrush, containerShape)
                backgroundColor != null && containerShape != null ->
                    Modifier.background(backgroundColor, containerShape)
                else -> Modifier
            }
        )
        .then(
            when {
                hasBorder && containerShape != null && borderBrush != null ->
                    Modifier.border(BorderStroke(borderWidth!!, borderBrush), containerShape)
                hasBorder && containerShape != null && borderColor != null ->
                    Modifier.border(borderWidth!!, borderColor, containerShape)
                else -> Modifier
            }
        )
        .then(if (containerShape != null) Modifier.clip(containerShape) else Modifier)

    Column(
        modifier = containerModifier.padding(
            start = contentPaddingStart,
            end = contentPaddingEnd,
            top = contentPaddingTop,
            bottom = contentPaddingBottom
        )
    ) {
        CompositionLocalProvider(
            LocalTextSelectionColors provides TextSelectionColors(
                handleColor = textStyle.color,
                backgroundColor = textStyle.color.copy(alpha = 0.3f),
            )
        ) {
        BasicTextField(
            modifier = textFieldModifier,
            value = textFieldValue,
            enabled = enabled,
            onValueChange = { newTextFieldValueState ->
                if (maxLength != null && newTextFieldValueState.text.length > maxLength) return@BasicTextField
                textFieldValueState = newTextFieldValueState

                val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
                lastTextValue = newTextFieldValueState.text

                if (stringChangedSinceLastInvocation) {
                    onValueChange(newTextFieldValueState.text)
                }
                if (onCursorPositionChange != null)
                    onCursorPositionChange(newTextFieldValueState.selection.start)
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
            maxLines = if (singleLine) ExtConstants.IntegerConstants.ONE else maxLines ?: Int.MAX_VALUE,
            decorationBox = {innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = textFieldValue.text,
                    interactionSource = interactionSource,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = singleLine,
                    visualTransformation =visualTransformation,
                    contentPadding = TextFieldDefaults.contentPaddingWithLabel(
                        start = AppDimens.Padding.zero,
                        bottom = AppDimens.Padding.padding4,
                        top =AppDimens.Padding.padding8,
                        end = AppDimens.Padding.zero
                    ),
                    label = {
                        Text(text = stringResource(label), fontWeight = FontWeight.W400, style = labelStyle)
                    },
                    leadingIcon = leadingContent,
                    trailingIcon = trailingContent,
                    isError = isTextFieldValid.not(),
                    colors = textFieldColors.value ,
                    container = {
                        TextFieldDefaults.Container(
                            enabled=enabled,
                            isError = isTextFieldValid.not(),
                            interactionSource = interactionSource,
                            colors = textFieldColors.value,
                            shape = TextFieldDefaults.shape,
                            focusedIndicatorLineThickness = AppDimens.EditText.borderWidth,
                            unfocusedIndicatorLineThickness = AppDimens.EditText.borderWidth
                        )
                    }
                )
            }

        )
        }
        if (supportText!=null){
            if ((supportText is CustomTextToDisplay.StringText && supportText.text.isBlank() )
                ||(supportText is CustomTextToDisplay.StringResourceText && stringResource(supportText.text).isBlank())
                ||(supportText is CustomTextToDisplay.AnnotatedStringText && supportText.text.isBlank())
                ) return
            CustomText(Modifier.padding(top = AppDimens.Padding.padding8),
                text= supportText,
                textStyle = supportTextStyle
            )
        }
    }
}


