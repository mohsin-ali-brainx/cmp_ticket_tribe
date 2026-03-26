package com.brainx.ticket_tribe.presentation.ui_components.dropdown_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.ticket_tribe.presentation.ui_components.text_fields.basic_text_field.CustomTextField
import com.brainx.ticket_tribe.utils.enums.CountryCode
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableSingleWithoutRipple
import com.brainx.utils_extensions.compose_ui_utils.safe_click.clickableWithoutRipple
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_down_arrow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> BasicDropdownField(
    label: UiText,
    selected: T?,
    options: List<T>,
    optionLabel: (T) -> String,
    expanded: Boolean,
    matchAnchorWidth: Boolean = true,
    dropdownWidth: Dp? = null,
    onExpandedChange: (Boolean) -> Unit,
    onSelected: (T) -> Unit,
    placeholder: UiText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY),
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    textFieldHeight: Dp = AppDimens.EditText.defaultTextFieldHeight,
    icon: DrawableResource = Res.drawable.ic_down_arrow
) {
    val theme = LocalAppTheme.current
    val fieldShape = RoundedCornerShape(AppDimens.Radius.radius14)

    Column(modifier = modifier.fillMaxWidth()) {
        if (showLabel){
            CustomText(
                text = label,
                fontSize = AppDimens.Fonts.font16,
                fontWeight = FontWeight.W200,
                color = theme.textView.primaryBlackTextColor,
                modifier = Modifier.padding(bottom = AppDimens.Padding.padding8)
            )

        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange,
        ) {

            CustomTextField(
                modifier =  Modifier
                    .fillMaxWidth()
                    .heightIn(min = textFieldHeight)
                    .focusProperties{ canFocus=false },
                text = selected?.let(optionLabel).orEmpty(),
                placeHolderText = (placeholder as UiText.StringText).text,
                borderColor = theme.border.borderColor2,
                borderWidth = AppDimens.EditText.borderWidth,
                trailingIcon = {
                    Image(painter = painterResource(icon), contentDescription = ExtConstants.StringConstants.EMPTY)
                },
                onValueChange = {

                }

            )

            ExposedDropdownMenu(
                containerColor = theme.background.whiteColor,
                shape = fieldShape,
                matchAnchorWidth = matchAnchorWidth,
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .then(
                        when {
                            dropdownWidth != null -> Modifier.width(dropdownWidth)
                            else -> Modifier
                        }
                    )
                    .heightIn(max = 350.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            CustomText(
                                text = UiText.StringText(optionLabel(option)),
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = AppDimens.Fonts.font14,
                                textAlign = TextAlign.Start,
                                color = theme.textView.primaryBlackTextColor,
                                fontWeight = FontWeight.W100,
                                minLines = 1,
                                maxLines = 2,
                                textOverflow = TextOverflow.Ellipsis,
                            )
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = theme.textView.primaryBlackTextColor,
                            leadingIconColor = theme.textView.primaryBlackTextColor,
                            trailingIconColor = theme.textView.primaryBlackTextColor
                        ),
                        onClick = {
                            onSelected(option)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}

/**
 * Underline-style dropdown field.
 *
 * - Non-editable, non-focusable "field" surface.
 * - Generic, supports custom selected/option content (e.g. flag + dial code).
 * - Falls back to plain [CustomText] when slots aren't provided.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> UnderlineDropdownField(
    label: UiText?=null,
    selected: T?,
    options: List<T>,
    optionLabel: (T) -> String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onSelected: (T) -> Unit,
    placeholder: UiText = UiText.StringText(text = ExtConstants.StringConstants.EMPTY),
    modifier: Modifier = Modifier,
    matchAnchorWidth: Boolean = true,
    dropdownWidth: Dp? = null,
    showLabel: Boolean = true,
    showDivider: Boolean = true,
    enabled: Boolean = true,
    isError: Boolean = false,
    textFieldHeight: Dp = AppDimens.EditText.defaultTextFieldHeight,
    indicatorThickness: Dp = AppDimens.EditText.borderWidth,
    icon: DrawableResource = Res.drawable.ic_down_arrow,
    selectedContent: (@Composable (T) -> Unit)? = null,
    optionContent: (@Composable (T) -> Unit)? = null,
) {
    val theme = LocalAppTheme.current
    val menuShape = RoundedCornerShape(AppDimens.Radius.radius14)

    val indicatorColor = when {
        !enabled -> theme.editText.disableColor
        isError -> theme.editText.redTextColor
        expanded -> theme.editText.primaryBlackTextColor
        else -> theme.editText.disableColor
    }

    Column(modifier = modifier.fillMaxWidth()) {
        if (showLabel) {
            CustomText(
                text = label?:UiText.StringText(text = ExtConstants.StringConstants.EMPTY),
                fontSize = AppDimens.Fonts.font16,
                fontWeight = FontWeight.W200,
                color = if (isError) theme.editText.redTextColor else theme.textView.primaryBlackTextColor,
                modifier = Modifier.padding(bottom = AppDimens.Padding.padding8)
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { if (enabled) onExpandedChange(!expanded) },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusProperties { canFocus = false }
                    .clickableSingleWithoutRipple(enabled = enabled) { onExpandedChange(!expanded) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(textFieldHeight)
                        .padding(bottom = AppDimens.Padding.padding2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        when {
                            selected != null && selectedContent != null -> selectedContent(selected)
                            selected != null -> CustomText(
                                modifier= Modifier.wrapContentSize(),
                                text = UiText.StringText(optionLabel(selected)),
                                fontSize = AppDimens.Fonts.font16,
                                fontWeight = FontWeight.W200,
                                color = theme.textView.primaryBlackTextColor,
                                minLines = 1,
                                maxLines = 1,
                                textOverflow = TextOverflow.Ellipsis
                            )
                            else -> CustomText(
                                modifier= Modifier.wrapContentSize(),
                                text = placeholder,
                                fontSize = AppDimens.Fonts.font16,
                                fontWeight = FontWeight.W200,
                                color = theme.textView.secondaryGreyTextColor,
                                minLines = 1,
                                maxLines = 1,
                                textOverflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(Modifier.width(AppDimens.Padding.padding8))
                    if (showDivider) {
                        VerticalDivider(
                            modifier = Modifier.padding(vertical = AppDimens.Padding.padding6)
                                .fillMaxHeight().padding(horizontal = AppDimens.Padding.padding4),
                            thickness = AppDimens.Dividers.dividerWidth1,
                            color = theme.divider.dividerColor3
                        )
                    }
                    Image(
                        painter = painterResource(icon),
                        contentDescription = ExtConstants.StringConstants.EMPTY
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(indicatorColor)
                        .heightIn(min = indicatorThickness, max = indicatorThickness)
                )
            }

            ExposedDropdownMenu(
                containerColor = theme.background.whiteColor,
                shape = menuShape,
                matchAnchorWidth = matchAnchorWidth,
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier
                    .then(
                        when {
                            dropdownWidth != null -> Modifier.width(dropdownWidth)
                            else -> Modifier
                        }
                    )
                    .heightIn(max = 350.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            if (optionContent != null) {
                                optionContent(option)
                            } else {
                                CustomText(
                                    text = UiText.StringText(optionLabel(option)),
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = AppDimens.Fonts.font14,
                                    textAlign = TextAlign.Start,
                                    color = theme.textView.primaryBlackTextColor,
                                    fontWeight = FontWeight.W100,
                                    minLines = 1,
                                    maxLines = 2,
                                    textOverflow = TextOverflow.Ellipsis,
                                )
                            }
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = theme.textView.primaryBlackTextColor,
                            leadingIconColor = theme.textView.primaryBlackTextColor,
                            trailingIconColor = theme.textView.primaryBlackTextColor
                        ),
                        onClick = {
                            onSelected(option)
                            onExpandedChange(false)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BasicDropdownFieldPreview(){
    AppTheme {
        var selectedMealType by remember { mutableStateOf<CountryCode?>(CountryCode.AE) }
        var expanded by remember { mutableStateOf(true) }
        Box(modifier = Modifier.fillMaxSize().padding(AppDimens.Padding.padding20), contentAlignment = Alignment.TopStart){
            BasicDropdownField(
                label = UiText.StringText("Select Country Code"),
                selected = selectedMealType,
                options = CountryCode.entries.toList(),
                optionLabel = { it.countryName },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                onSelected = { selectedMealType = it },
                placeholder = UiText.StringText("Select Country Code")
            )
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun UnderlineDropdownFieldPreview(){
    AppTheme {
        var selectedCode by remember { mutableStateOf<CountryCode?>(CountryCode.US) }
        var expanded by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier.fillMaxSize().padding(AppDimens.Padding.padding20),
            contentAlignment = Alignment.TopStart
        ){
            UnderlineDropdownField(
                modifier = Modifier.fillMaxWidth(0.3f).clickableWithoutRipple{
                    expanded=!expanded
                },
                showLabel = false,
                matchAnchorWidth = true,
                selected = selectedCode,
                options = CountryCode.sortedByDialCode,
                optionLabel = { "${it.flag} ${it.dialCode}" },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                onSelected = { selectedCode = it },
                placeholder = UiText.StringText("Select Country Code"),
                selectedContent = { code ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomText(modifier = Modifier.wrapContentSize(),text = UiText.StringText(code.flag))
                        Spacer(Modifier.width(6.dp))
                        CustomText(modifier = Modifier.wrapContentSize(),text = UiText.StringText(code.dialCode))
                    }
                },
                optionContent = { code ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(modifier = Modifier.wrapContentSize(),text = UiText.StringText(code.flag))
                        Spacer(Modifier.width(10.dp))
                        CustomText(
                            text = UiText.StringText("${code.dialCode}  •  ${code.countryName}"),
                            modifier = Modifier.weight(1f),
                            minLines = 1,
                            maxLines = 1,
                            textOverflow = TextOverflow.Ellipsis
                        )
                    }
                }
            )
        }
    }
}
