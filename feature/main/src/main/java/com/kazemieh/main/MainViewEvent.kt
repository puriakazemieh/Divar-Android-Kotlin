package com.kazemieh.main

import androidx.compose.runtime.Stable
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState

@Stable
data class MainUiState(
    val isLoading: Boolean = true,
) : UiState


sealed class MainUiEvent : UiEvent {
}
