package com.brainx.ticket_tribe.presentation.ui_components.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    textModifier:Modifier = Modifier.wrapContentSize(),
    buttonText: UiText,
    buttonColor: Color? = null,
    buttonBrush: Brush? = null,
    textColor: Color,
    borderColor: Color? = null,
    borderBrush: Brush? = null,
    borderWidth: Dp = AppDimens.Button.zero,
    elevation:Dp =  AppDimens.Button.zero,
    isClickable:Boolean=true,
    showLoader:Boolean=false,
    buttonRadius:Dp=AppDimens.Radius.radius12,
    buttonShape: Shape = RoundedCornerShape(buttonRadius),
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.W500,
    fontStyle: FontStyle = FontStyle.Normal,
    /** When true and [trailingIcon] is set, the label row uses [Modifier.weight] so text stays start-aligned and the icon stays at the end. */
    expandTextBetweenTrailingIcon: Boolean = false,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: ()->Unit
){
    if (buttonColor != null && buttonBrush != null) {
        throw IllegalArgumentException("Only one of buttonColor or buttonBrush can be specified.")
    }
    if (borderColor != null && borderBrush != null) {
        throw IllegalArgumentException("Only one of borderColor or borderBrush can be specified.")
    }

    val resolvedButtonColor = when {
        buttonBrush != null -> Color.Transparent
        buttonColor != null -> buttonColor
        else -> MaterialTheme.colorScheme.primary
    }

    val borderStroke: BorderStroke? = when {
        borderBrush != null && borderWidth > AppDimens.Button.zero ->
            BorderStroke(borderWidth, borderBrush)
        borderColor != null && borderWidth > AppDimens.Button.zero ->
            BorderStroke(borderWidth, borderColor)
        else -> null
    }

    val buttonModifier = if (buttonBrush != null) {
        modifier.background(buttonBrush, buttonShape)
    } else {
        modifier
    }

    val localDensity = LocalDensity.current
    val loaderSize = remember { with(localDensity) {
        fontSize.toDp()
    }}
    Button(
        onClick = { if (isClickable) onClickAction() else null},
        elevation = ButtonDefaults.buttonElevation(defaultElevation = elevation),
        enabled = isClickable,
        modifier = buttonModifier,
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = resolvedButtonColor),
        border = borderStroke
    ) {
        if(expandTextBetweenTrailingIcon){

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(Modifier.width(AppDimens.Padding.iconPadding))
                }

                CustomText(
                    modifier = textModifier.then(
                        if (expandTextBetweenTrailingIcon && trailingIcon != null) Modifier.weight(1f)
                        else Modifier
                    ),
                    text = buttonText,
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textAlign = textAlign,
                    fontStyle = fontStyle,
                )

                AnimatedVisibility(showLoader){
                    CircularProgressIndicator(
                        Modifier.padding(AppDimens.Padding.iconPadding).size(loaderSize),
                        color = textColor
                    )
                }
                if (trailingIcon != null) {
                    Spacer(Modifier.width(AppDimens.Padding.iconPadding))
                    trailingIcon()
                }
            }
        }
        else {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(Modifier.width(AppDimens.Padding.iconPadding))
            }

            CustomText(
                modifier = textModifier,
                text = buttonText,
                color = textColor,
                fontSize = fontSize,
                fontWeight = fontWeight,
                textAlign = textAlign,
                fontStyle = fontStyle,
            )

            AnimatedVisibility(showLoader){
                CircularProgressIndicator(
                    Modifier.padding(AppDimens.Padding.iconPadding).size(loaderSize),
                    color = textColor
                )
            }
            if (trailingIcon != null) {
                Spacer(Modifier.width(AppDimens.Padding.iconPadding))
                trailingIcon()
            }
        }
    }
}

@Composable
fun ExpandedCustomButton(
    modifier: Modifier = Modifier,
    textModifier:Modifier = Modifier.fillMaxWidth(1f),
    buttonText: UiText,
    buttonColor: Color? = null,
    buttonBrush: Brush? = null,
    textColor: Color,
    borderColor: Color? = null,
    borderBrush: Brush? = null,
    borderWidth: Dp = AppDimens.Button.zero,
    elevation:Dp =  AppDimens.Button.zero,
    isClickable:Boolean=true,
    showLoader:Boolean=false,
    buttonRadius:Dp=AppDimens.Radius.radius12,
    buttonShape: Shape = RoundedCornerShape(buttonRadius),
    fontSize: TextUnit = AppDimens.Fonts.font16,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.W500,
    fontStyle: FontStyle = FontStyle.Normal,
    /** When true and [trailingIcon] is set, the label row uses [Modifier.weight] so text stays start-aligned and the icon stays at the end. */
    expandTextBetweenTrailingIcon: Boolean = true,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    onClickAction: ()->Unit
){
    if (buttonColor != null && buttonBrush != null) {
        throw IllegalArgumentException("Only one of buttonColor or buttonBrush can be specified.")
    }
    if (borderColor != null && borderBrush != null) {
        throw IllegalArgumentException("Only one of borderColor or borderBrush can be specified.")
    }

    val resolvedButtonColor = when {
        buttonBrush != null -> Color.Transparent
        buttonColor != null -> buttonColor
        else -> MaterialTheme.colorScheme.primary
    }

    val borderStroke: BorderStroke? = when {
        borderBrush != null && borderWidth > AppDimens.Button.zero ->
            BorderStroke(borderWidth, borderBrush)
        borderColor != null && borderWidth > AppDimens.Button.zero ->
            BorderStroke(borderWidth, borderColor)
        else -> null
    }

    val buttonModifier = if (buttonBrush != null) {
        modifier.background(buttonBrush, buttonShape)
    } else {
        modifier
    }

    val localDensity = LocalDensity.current
    val loaderSize = remember { with(localDensity) {
        fontSize.toDp()
    }}
    Button(
        onClick = { if (isClickable) onClickAction() else null},
        elevation = ButtonDefaults.buttonElevation(defaultElevation = elevation),
        enabled = isClickable,
        modifier = buttonModifier,
        shape = buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = resolvedButtonColor),
        border = borderStroke
    ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(Modifier.width(AppDimens.Padding.iconPadding))
                }

                CustomText(
                    modifier = textModifier.then(
                        if (expandTextBetweenTrailingIcon && trailingIcon != null) Modifier.weight(1f)
                        else Modifier
                    ),
                    text = buttonText,
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textAlign = textAlign,
                    fontStyle = fontStyle,
                )

                AnimatedVisibility(showLoader){
                    CircularProgressIndicator(
                        Modifier.padding(AppDimens.Padding.iconPadding).size(loaderSize),
                        color = textColor
                    )
                }
                if (trailingIcon != null) {
                    Spacer(Modifier.width(AppDimens.Padding.iconPadding))
                    trailingIcon()
                }
            }
    }
}
