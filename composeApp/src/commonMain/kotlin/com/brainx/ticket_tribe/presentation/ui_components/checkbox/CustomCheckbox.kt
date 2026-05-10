package com.brainx.ticket_tribe.presentation.ui_components.checkbox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.ticket_tribe.presentation.theme.AppTheme
import com.brainx.ticket_tribe.presentation.theme.colors.LocalAppTheme
import com.brainx.ticket_tribe.presentation.ui_components.text.CustomText
import com.brainx.ticket_tribe.presentation.ui_components.text.UiText
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.agree_terms
import tickettribecmp.composeapp.generated.resources.ic_checked
import tickettribecmp.composeapp.generated.resources.ic_unchecked

@Composable
fun DefaultCheckbox(
    modifier: Modifier,
    checked:Boolean=false,
    label: UiText,
    onCheckedChange:((Boolean)->Unit)?=null
){
    val theme = LocalAppTheme.current
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = theme.icon.blackIconColor, uncheckedColor = theme.icon.blackIconColor.copy(alpha = 0.5f))
        )
        Spacer(Modifier.width(AppDimens.Padding.padding8))
        CustomText(modifier = Modifier.wrapContentSize(), text = label, fontSize = AppDimens.Fonts.font14, fontWeight = FontWeight.W100, color = theme.textView.secondaryGreyTextColor)
    }
}

@Composable
fun CustomCheckbox(
    modifier: Modifier,
    checked:Boolean=false,
    label: UiText,
){
    val theme = LocalAppTheme.current
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(if(checked) Res.drawable.ic_checked else  Res.drawable.ic_unchecked), contentDescription = ExtConstants.StringConstants.EMPTY)
        Spacer(Modifier.width(AppDimens.Padding.padding8))
        CustomText(modifier = Modifier.wrapContentSize(), text = label, fontSize = AppDimens.Fonts.font14, fontWeight = FontWeight.W100, color = theme.textView.secondaryGreyTextColor)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DefaultCheckboxPreview(){
    AppTheme {
        DefaultCheckbox(modifier = Modifier, label = UiText.StringResourceText(Res.string.agree_terms))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun DefaultCheckboxPreview_Checked(){
    AppTheme {
        DefaultCheckbox(modifier = Modifier, checked = true,label = UiText.StringResourceText(Res.string.agree_terms))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CustomCheckboxPreview(){
    AppTheme {
        CustomCheckbox(modifier = Modifier, label = UiText.StringResourceText(Res.string.agree_terms))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CustomCheckboxPreview_Checked(){
    AppTheme {
        CustomCheckbox(modifier = Modifier, checked = true,label = UiText.StringResourceText(Res.string.agree_terms))
    }
}