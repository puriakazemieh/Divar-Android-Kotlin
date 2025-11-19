package com.kazemieh.search

import androidx.compose.runtime.Stable
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState
import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.ui.model.FromScreen
import kotlinx.collections.immutable.ImmutableList

@Stable
data class SearchUiState(
    val isLoading: Boolean = false,
    val categoriesOfAds: ImmutableList<CategoryOfAds> = immutableListOf(),
    val selectedCategoryOfAds: CategoryOfAds? = null,
    val fromScreen: FromScreen = FromScreen.Home,
    val adsFilter: AdsFilter? = null
) : UiState


sealed class SearchUiEvent : UiEvent {
    data object OnRefresh : SearchUiEvent()
    data class OnChangeText(val searchText: String) : SearchUiEvent()
    data class OnSelect(val categoryOfAds: CategoryOfAds) : SearchUiEvent()
}

typealias OnAction = (SearchUiEvent) -> Unit