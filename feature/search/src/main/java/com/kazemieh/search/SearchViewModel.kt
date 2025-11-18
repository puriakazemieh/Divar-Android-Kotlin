package com.kazemieh.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.usecase.category.GetCategoriesOfAdsUseCase
import com.kazemieh.domain.usecase.location.GetUserCityUseCase
import com.kazemieh.ui.model.UiMessage
import com.kazemieh.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesOfAdsUseCase: GetCategoriesOfAdsUseCase,
    private val getUserCityUseCase: GetUserCityUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>() {
    private var searchJob: Job? = null
    private var cityId: Long? = null

    init {
        getInitData()
        getUserCity()
    }

    private fun getInitData() {
        savedStateHandle?.get<String>("searchText")?.takeIf { it != "nulll" }?.let {
            setState { copy(searchText = it) }
        }
    }

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    cityId = it.id
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }


    override fun createInitialState() = SearchUiState()

    override fun onTriggerEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnChangeText -> {
                searchJob?.cancel()
                setState { copy(searchText = event.searchText) }
                searchJob = viewModelScope.launch {
                    delay(1500)
                    getCategoriesOfAds()
                }
            }

            is SearchUiEvent.OnSelect -> {
                setState { copy(selectedCategoryOfAds = event.categoryOfAds) }
            }

            SearchUiEvent.OnRefresh -> getCategoriesOfAds()
        }
    }

    private fun getCategoriesOfAds() {
        if (currentState.searchText.isEmpty()) return
        if (cityId == null) {
            setUiMessage(UiMessage(intValue = com.kazemieh.ui.R.string.please_choose_city))
            return
        }
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            getCategoriesOfAdsUseCase.invoke(currentState.searchText, cityId!!).collect {
                it.onSuccess {
                    setState { copy(isLoading = false, categoriesOfAds = it.toImmutableList()) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }
}
