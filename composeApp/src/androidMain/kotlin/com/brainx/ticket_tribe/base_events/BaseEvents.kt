package com.brainx.ticket_tribe.base_events

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class BaseUiEvents {
    data class UpdateSystemColors(val statusBarColor:Color=Color.Transparent,val navBarColor:Color=Color.Transparent):BaseUiEvents()
    data object CloseApp:BaseUiEvents()
}

object Events{
    private val _baseEventFlow = Channel<BaseUiEvents>()
    val baseEventFlow = _baseEventFlow.receiveAsFlow()

    suspend fun updateBaseEvent(event: BaseUiEvents) {
        _baseEventFlow.send(event)
    }
}