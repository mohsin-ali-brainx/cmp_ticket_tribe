package com.brainx.cmp_base.presentation.screens.main_home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brainx.cmp_base.presentation.screens.main_home.ui_events.MainHomeScreenUiEvents
import com.brainx.cmp_base.presentation.screens.main_home.ui_intents.MainHomeScreenUiIntents
import com.brainx.cmp_base.presentation.screens.main_home.ui_state.MainHomeScreenUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainHomeScreenViewModel(
    private val ioDispatcher : CoroutineDispatcher,
): ViewModel() {
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(MainHomeScreenUiState())
    val state = _state
        // Only emit when state actually changes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 500),
            initialValue = MainHomeScreenUiState()
        )

    private val _eventFlow = Channel<MainHomeScreenUiEvents>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private fun emitUIEvents(event: MainHomeScreenUiEvents){
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }


    fun onIntent(intent: MainHomeScreenUiIntents) {
        when (intent) {
            else->Unit
        }
    }

}