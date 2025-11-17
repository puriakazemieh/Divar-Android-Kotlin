package com.kazemieh.location

import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.location.City
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class LocationUiState(
    val isLoading: Boolean = true,
    val searchText: String = "",
    val cities: ImmutableList<City>? = null,
    val cityIsSelected: Boolean = false,
    val selectedCity: City? = null,
) : UiState


sealed class LocationUiEvent : UiEvent {
    data object OnRefresh : LocationUiEvent()
    data class OnSearch(val text: String) : LocationUiEvent()
    data class OnCity(val city: City) : LocationUiEvent()
}

typealias OnAction = (LocationUiEvent) -> Unit