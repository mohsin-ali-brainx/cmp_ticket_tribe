package com.brainx.cmp_base.presentation.ui_components.text_fields.basic_text_field

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brainx.cmp_base.presentation.theme.AppDimens
import com.brainx.cmp_base.presentation.theme.defaultEditTextShape
import com.brainx.cmp_base.presentation.ui_components.text_fields.editTextStyle
import com.brainx.utils_extensions.constants.ExtConstants

@Composable
private fun CustomBasicTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color = Color.White,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: Color.Gray
    ),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    enabled:Boolean=true,
    hintTextAlignment: Alignment = Alignment.Center,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    visualTransformation: VisualTransformation=VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onCursorPositionChange: ((Int) -> Unit)?=null,
    onValueChange: (String) -> Unit,
){

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

    BasicTextField(
        modifier= modifier,
        value = textFieldValue,
        cursorBrush = SolidColor(cursorBrush),
        enabled = enabled,
        onValueChange = { newTextFieldValueState ->
            if (maxLength!=null && newTextFieldValueState.text.length>maxLength) return@BasicTextField
            textFieldValueState = newTextFieldValueState

            val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
            lastTextValue = newTextFieldValueState.text

            if (stringChangedSinceLastInvocation) {
                onValueChange(newTextFieldValueState.text)
            }
            if (onCursorPositionChange!=null)
                onCursorPositionChange(newTextFieldValueState.selection.start)

        },
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Password) KeyboardCapitalization.None else KeyboardCapitalization.Sentences,
            autoCorrectEnabled = false,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines?: ExtConstants.IntegerConstants.ONE,
        maxLines = if (singleLine) ExtConstants.IntegerConstants.ONE else maxLines?:Int.MAX_VALUE,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = hintTextAlignment
            ) {
                // Use textFieldValue.text so placeholder visibility is in sync with what's displayed
                if (textFieldValue.text.isEmpty()) {
                    placeHolderText?.let {
                        Text(
                            modifier = Modifier.padding(AppDimens.Padding.zero),
                            text = it,
                            textAlign = TextAlign.Start,
                            style = hintTextStyle,
                        )
                    }
                }
                innerTextField()
            }
        })
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    placeHolderText:String?=null,
    cursorBrush: Color = Color.Black,
    textStyle: TextStyle = editTextStyle(),
    hintTextColor: Color?=null,
    hintTextStyle: TextStyle = editTextStyle(
        textColor = hintTextColor ?: Color.Gray
    ),
    maxLength:Int?=null,
    singleLine:Boolean=true,
    minLines:Int?=null,
    maxLines:Int?=null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    visualTransformation: VisualTransformation=VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    paddingLeadingIconEnd: Dp = AppDimens.Padding.iconPadding,
    paddingTrailingIconStart: Dp = AppDimens.Padding.iconPadding,
    paddingTrailingIconEnd: Dp = AppDimens.Padding.iconPadding,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onCursorPositionChange: ((Int) -> Unit)? = null,
    backgroundColor: Color? = null,
    backgroundBrush: Brush? = null,
    shape: Shape? = null,
    borderColor: Color? = null,
    borderBrush: Brush? = null,
    borderWidth: Dp? = null,
    contentPaddingStart: Dp = AppDimens.Padding.padding8,
    contentPaddingEnd: Dp = AppDimens.Padding.padding8,
    contentPaddingTop: Dp = AppDimens.Padding.padding8,
    contentPaddingBottom: Dp = AppDimens.Padding.padding8,
) {
    if (backgroundColor != null && backgroundBrush != null) {
        throw IllegalArgumentException("CustomTextField: 'backgroundColor' and 'backgroundBrush' cannot be used together. Use only one.")
    }
    if (borderColor != null && borderBrush != null) {
        throw IllegalArgumentException("CustomTextField: 'borderColor' and 'borderBrush' cannot be used together. Use only one.")
    }

    val hasBorder = borderWidth != null && borderWidth > 0.dp && (borderColor != null || borderBrush != null)
    val hasStyling = backgroundColor != null || backgroundBrush != null || shape != null || hasBorder
    val containerShape = when {
        shape != null -> shape
        hasStyling -> defaultEditTextShape()
        else -> null
    }
    val layoutModifier = Modifier
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
        .padding(
            start = contentPaddingStart,
            end = contentPaddingEnd,
            top = contentPaddingTop,
            bottom = contentPaddingBottom
        )
    Layout(
        modifier = modifier.then(layoutModifier),
        content = {
            if (leadingIcon != null) leadingIcon()
            Box(
                modifier = Modifier.then(
                    if (leadingIcon != null && trailingIcon != null){
                        Modifier.padding(
                            start = paddingLeadingIconEnd,
                            end = paddingTrailingIconStart
                        )
                    }else if (leadingIcon != null){
                        Modifier.padding(
                            start = paddingLeadingIconEnd
                        )
                    }else if (trailingIcon != null){
                        Modifier.padding(
                            start = AppDimens.Padding.padding4,
                            end = paddingTrailingIconStart
                        )
                    }else{
                        Modifier.padding(
                                horizontal = AppDimens.Padding.padding4,
                            )
                    }
                )
            ) {
                CustomBasicTextField(
                    text = text,
                    cursorBrush = cursorBrush,
                    textStyle = textStyle,
                    placeHolderText = placeHolderText,
                    hintTextColor = hintTextColor,
                    maxLength = maxLength,
                    keyboardType = keyboardType,
                    singleLine = singleLine,
                    minLines = minLines,
                    maxLines = maxLines,
                    hintTextStyle = hintTextStyle,
                    imeAction = imeAction,
                    visualTransformation = visualTransformation,
                    hintTextAlignment = Alignment.TopStart,
                    keyboardActions = keyboardActions,
                    onValueChange = { onValueChange(it) },
                    onCursorPositionChange = onCursorPositionChange
                )
            }
            if (trailingIcon != null) {
                Box(modifier = Modifier.padding(end = paddingTrailingIconEnd)) {
                    trailingIcon()
                }
            }
        }
    ) { measurables, constraints ->
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val leadingPlaceable = if (leadingIcon != null) measurables[0].measure(looseConstraints) else null
        val centerIndex = if (leadingIcon != null) 1 else 0
        val trailingPlaceable = if (trailingIcon != null) measurables[centerIndex + 1].measure(looseConstraints) else null
        val leadingWidth = leadingPlaceable?.width ?: 0
        val trailingWidth = trailingPlaceable?.width ?: 0
        val centerWidth = (constraints.maxWidth - leadingWidth - trailingWidth)
            .coerceAtLeast(0)
        val centerConstraints = constraints.copy(
            minWidth = centerWidth,
            maxWidth = centerWidth,
            minHeight = 0
        )
        val centerPlaceable = measurables[centerIndex].measure(centerConstraints)
        val height = maxOf(
            leadingPlaceable?.height ?: 0,
            centerPlaceable.height,
            trailingPlaceable?.height ?: 0
        ).coerceIn(constraints.minHeight, constraints.maxHeight)
        layout(constraints.maxWidth, height) {
            var x = 0
            leadingPlaceable?.placeRelative(x, (height - (leadingPlaceable.height)) / 2)
            x += leadingWidth
            centerPlaceable.placeRelative(x, (height - centerPlaceable.height) / 2)
            x += centerWidth
            trailingPlaceable?.placeRelative(x, (height - (trailingPlaceable.height)) / 2)
        }
    }
}
