package com.brainx.ticket_tribe.presentation.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.brainx.ticket_tribe.base_events.BaseUiEvents
import com.brainx.ticket_tribe.base_events.Events
import kotlinx.coroutines.launch

@Composable
actual fun PlatformColors(
    statusBarColor: Color,
    navBarColor: Color
) {
    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(statusBarColor, navBarColor) {
        val job = coroutineScope.launch {
            Events.updateBaseEvent(
                BaseUiEvents.UpdateSystemColors(
                    statusBarColor = statusBarColor,
                    navBarColor = navBarColor
                )
            )
        }

        job.invokeOnCompletion { throwable ->
            if(throwable!=null){
                throwable.printStackTrace()
            }else{
                job.cancel()
            }
        }

        onDispose {
            job.cancel()
        }
    }
}