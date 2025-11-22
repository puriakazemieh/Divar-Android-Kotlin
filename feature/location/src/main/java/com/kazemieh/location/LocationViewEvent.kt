package com.kazemieh.location

import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.location.City
import com.kazemieh.domain.model.location.LocationScreenType
import com.kazemieh.domain.model.location.Neighborhood
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
    val locationScreenType: LocationScreenType = LocationScreenType.FromLogin,

    val selectedNeighborhood: Neighborhood? = null,
    val onBack: Boolean = false,
) : UiState


sealed class LocationUiEvent : UiEvent {
    data object OnRefresh : LocationUiEvent()
    data class OnSearch(val text: String) : LocationUiEvent()
    data class OnCity(val city: City) : LocationUiEvent()
    data class OnNeighborhood(val neighborhood: Neighborhood) : LocationUiEvent()

}

typealias OnAction = (LocationUiEvent) -> Unit