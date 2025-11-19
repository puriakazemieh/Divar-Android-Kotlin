package com.kazemieh.ads

import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.ads.AdsSummary
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.model.filter.FilterClickType
import com.kazemieh.domain.model.location.City
import com.kazemieh.domain.model.paginate.Paging
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.ui.model.FromScreen
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class AdsUiState(
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val ads: Paging<ImmutableList<AdsSummary>>? = Paging(content = immutableListOf()),
    val page: Int = 0,
    val userCity: City? = null,
    val adsFilter: AdsFilter? = null,
    val navigateToFilter: FilterClickType? = null,
    val navigateToNeighborhood: Boolean = false,
    val showCategoryDialog: Boolean = false,
    val categories: ImmutableList<Category> = immutableListOf(),
    val fromScreen: FromScreen = FromScreen.Home,

    ) : UiState


sealed class AdsUiEvent : UiEvent {
    data object OnRefresh : AdsUiEvent()
    data object OnLoadMore : AdsUiEvent()
    data object OnDismissDialog : AdsUiEvent()
    data object OnNavigated : AdsUiEvent()
    data class OnFilterClickType(val filterClickType: FilterClickType) : AdsUiEvent()
}

typealias OnAction = (AdsUiEvent) -> Unit
