package com.kazemieh.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kazemieh.domain.model.paginate.addMore
import com.kazemieh.domain.usecase.ads.GetAdsSummaryUseCase
import com.kazemieh.ui.model.UiMessage
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.usecase.category.GetCategoriesUseCase
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsSummaryUseCase: GetAdsSummaryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<HomeUiState, HomeUiEvent>() {

    init {
        getCategories()
        getAds()
    }

    override fun createInitialState() = HomeUiState()

    override fun onTriggerEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnLoadMore -> {
                if (currentState.ads?.isLast == false) {
                    setState { copy(page = currentState.page + 1) }
                    getAds()
                }
            }

            HomeUiEvent.OnRefreshing -> {
                setState { copy(page = 0) }
                getAds()
                if (currentState.categories.isNullOrEmpty())
                    getCategories()
            }

            is HomeUiEvent.OnSelectedCategory -> {
                setState {
                    copy(
                        selectedCategories =
                            selectedCategories?.toMutableList()?.apply { add(event.category) }
                                ?.toImmutableList()
                                ?: listOf(event.category).toImmutableList(),
                        showCategories = event.category.children.toImmutableList()
                    )
                }
            }

            HomeUiEvent.OnRemoveSelectedCategory -> {
                val tempSelected =
                    currentState.selectedCategories?.toMutableList()?.apply { removeLastOrNull() }
                        ?.toImmutableList()
                setState {
                    copy(
                        selectedCategories = tempSelected,
                        showCategories = tempSelected?.lastOrNull()?.children?.toImmutableList()
                    )
                }
            }

            HomeUiEvent.OnClearCategory -> {
                setState {
                    copy(
                        showCategories = immutableListOf(),
                        selectedCategories = immutableListOf()
                    )
                }
            }

            is HomeUiEvent.OnCategorySearchChange -> {
                setState {
                    copy(
                        categorySearchText = event.text,
                        searchedCategories = findCategoriesByName(
                            currentState.categories,
                            event.text
                        )
                    )
                }
            }
        }
    }

    private fun findCategoriesByName(
        categories: List<Category>?,
        searchText: String
    ): ImmutableList<Category> {
        val temp = mutableListOf<Category>()

        categories?.forEach { category ->
            if (category.name.contains(searchText, ignoreCase = true)) {
                temp.add(category)
            }
            if (category.children.isNotEmpty()) {
                temp.addAll(findCategoriesByName(category.children, searchText))
            }
        }

        return temp.toImmutableList()
    }


    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess { categories ->
                    var size = categories.size
                    while (size % 4 != 0) {
                        size++
                    }
                    setState {
                        copy(
                            isLoading = false,
                            emptyCategoryCount = size - categories.size,
                            categories = categories.toImmutableList()
                        )
                    }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun getAds() {
        if (currentState.page > 0) setState { copy(isLoadMore = true) }
        else setState { copy(isLoading = true) }
        viewModelScope.launch {
            getAdsSummaryUseCase.invoke(currentState.page).collect {
                it.onSuccess { paging ->
                    if (paging.isFirst || currentState.ads?.content.isNullOrEmpty()) {
                        setState { copy(isLoading = false, isLoadMore = false, ads = paging) }
                    } else {
                        setState {
                            copy(
                                isLoading = false,
                                isLoadMore = false,
                                ads = ads?.addMore(
                                    paging = paging,
                                    content = (ads.content + paging.content).toImmutableList()
                                )
                            )
                        }
                    }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false, isLoadMore = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

}
