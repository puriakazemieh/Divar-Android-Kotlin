package com.kazemieh.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kazemieh.domain.fake_data.fromJson
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.usecase.category.GetCategoriesOfAdsUseCase
import com.kazemieh.domain.usecase.filter.ReadFilterFromCategoryUseCase
import com.kazemieh.domain.usecase.filter.ReadFilterFromHomeUseCase
import com.kazemieh.domain.usecase.filter.SaveFilterFromCategoryUseCase
import com.kazemieh.domain.usecase.filter.SaveFilterFromHomeUseCase
import com.kazemieh.domain.usecase.location.GetUserCityUseCase
import com.kazemieh.ui.model.FromScreen
import com.kazemieh.ui.model.UiMessage
import com.kazemieh.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke
import kotlin.onFailure
import kotlin.onSuccess

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesOfAdsUseCase: GetCategoriesOfAdsUseCase,
    private val getUserCityUseCase: GetUserCityUseCase,
    private val readFilterFromCategoryUseCase: ReadFilterFromCategoryUseCase,
    private val readFilterFromHomeUseCase: ReadFilterFromHomeUseCase,

    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase,
    private val saveFilterFromHomeUseCase: SaveFilterFromHomeUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>() {
    private var searchJob: Job? = null
    private var cityId: Long? = null

    init {
        getInitData()
        getUserCity()
    }

    private fun getInitData() {
        savedStateHandle?.get<String>("fromScreen")?.let { json ->
            setState { copy(fromScreen = json.fromJson<FromScreen>()!!) }
            getAdsFilter()
        }
    }

    private fun getAdsFilter() {
        viewModelScope.launch {
            when (currentState.fromScreen) {
                FromScreen.Home -> {
                    readFilterFromHomeUseCase.invoke().collect {
                        setState { copy(adsFilter = it ?: AdsFilter(searchText = "")) }
                    }
                }

                FromScreen.Category -> {
                    readFilterFromCategoryUseCase.invoke().collect {
                        setState { copy(adsFilter = it ?: AdsFilter(searchText = "")) }
                    }
                }
            }
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
                setState { copy(adsFilter = adsFilter?.copy(searchText = event.searchText)) }
                searchJob = viewModelScope.launch {
                    delay(1500)
                    getCategoriesOfAds()
                }
            }

            is SearchUiEvent.OnSelect -> {
                setState {
                    copy(
                        selectedCategoryOfAds = event.categoryOfAds,
                        adsFilter = adsFilter?.copy(
                            category = Category(
                                name = event.categoryOfAds.categoryName,
                                id = event.categoryOfAds.categoryId,
                                icon = "",
                                children = listOf()
                            )
                        )
                    )
                }
                saveFilter()
            }

            SearchUiEvent.OnRefresh -> getCategoriesOfAds()
        }
    }

    private fun saveFilter() {
        viewModelScope.launch {
            when (currentState.fromScreen) {
                FromScreen.Home -> saveFilterFromHomeUseCase.invoke(currentState.adsFilter)
                FromScreen.Category -> saveFilterFromCategoryUseCase.invoke(currentState.adsFilter)
            }
        }
    }

    private fun getCategoriesOfAds() {
        if (currentState.adsFilter?.searchText.isNullOrEmpty()) return
        if (cityId == null) {
            setUiMessage(UiMessage(intValue = com.kazemieh.ui.R.string.please_choose_city))
            return
        }
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            getCategoriesOfAdsUseCase.invoke(currentState.adsFilter?.searchText ?: "", cityId!!).collect {
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
