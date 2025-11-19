package com.kazemieh.ads_detail

import android.content.Context
import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState

@Stable
data class AdsDetailUiState(
    val isLoading: Boolean = true,
    val ads: Ads? = null,
    val showFullScreenSlider: Boolean = false
) : UiState


sealed class AdsDetailUiEvent : UiEvent {
    data object OnRefresh : AdsDetailUiEvent()
    data class ShowFullScreenSlider(val isFullScreen: Boolean) : AdsDetailUiEvent()
    data class OnShareClick(val context: Context) : AdsDetailUiEvent()
}

typealias OnAction = (AdsDetailUiEvent) -> Unit
