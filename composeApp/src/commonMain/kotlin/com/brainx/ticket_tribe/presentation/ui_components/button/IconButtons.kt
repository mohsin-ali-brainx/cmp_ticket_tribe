package com.brainx.ticket_tribe.presentation.ui_components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.brainx.ticket_tribe.presentation.theme.AppDimens
import com.brainx.utils_extensions.constants.ExtConstants
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tickettribecmp.composeapp.generated.resources.Res
import tickettribecmp.composeapp.generated.resources.ic_upload

@Composable
fun IconButton(
    modifier: Modifier,
    icon: DrawableResource,
){

    Box(
        modifier = modifier,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ){
        Image(painter = painterResource(icon), contentDescription = ExtConstants.StringConstants.EMPTY)
    }

}