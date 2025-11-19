package com.kazemieh.main

import androidx.compose.runtime.Stable
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState

@Stable
data class MainUiState(
    val isLoading: Boolean = true,
    val selectedIndex: Int = 4,
    val isUserLoggedIn: Boolean = false
) : UiState


sealed class MainUiEvent : UiEvent {
    data class OnChangeTab(val index: Int) : MainUiEvent()
}

typealias OnAction = (MainUiEvent) -> Unit
