package com.kazemieh.splash

import androidx.compose.runtime.Stable
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState

@Stable
data class SplashUiState(
    val userIsSelectedCity: Boolean? = null,
) : UiState


sealed class SplashUiEvent : UiEvent {
}
