package com.kazemieh.feature

import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.domain.model.filter.FilterClickType
import com.kazemieh.ui.model.FromScreen
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class FilterUiState(
    val isLoading: Boolean = true,
    val adsFilter: AdsFilter? = null,
    val filterClickType: FilterClickType? = null,
    val minPrice: String = "",
    val maxPrice: String = "",

    val showCategoryDialog: Boolean = false,
    val showParameterDialog: Parameter? = null,

    val allCategories: ImmutableList<Category> = immutableListOf(),

    val fromScreen: FromScreen = FromScreen.Home
) : UiState


sealed class FilterUiEvent : UiEvent {
    data class OnFilterClickType(val filterClickType: FilterClickType) : FilterUiEvent()
    data class OnMaxPriceChange(val value: String) : FilterUiEvent()
    data class OnMinPriceChange(val value: String) : FilterUiEvent()

    // when user select an option in parameter dialog
    data class OnAnswerToParameter(val parameter: Parameter) : FilterUiEvent()

    data object DismissDialog : FilterUiEvent()

    data object OnClearFilter : FilterUiEvent()
    data object OnSaveFilter : FilterUiEvent()
}

typealias OnAction = (FilterUiEvent) -> Unit
