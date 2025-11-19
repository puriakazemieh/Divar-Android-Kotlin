package com.kazemieh.create_ads

import androidx.lifecycle.viewModelScope
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.model.parameter.DataType
import com.kazemieh.domain.usecase.ads.CreateAdsUseCase
import com.kazemieh.domain.usecase.category.GetCategoriesUseCase
import com.kazemieh.domain.usecase.parameter.GetParametersUseCase
import com.kazemieh.ui.R
import com.kazemieh.ui.extension.findIndex
import com.kazemieh.ui.model.MessageStatus
import com.kazemieh.ui.model.MessageType
import com.kazemieh.ui.model.UiMessage
import com.kazemieh.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAdsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getParametersUseCase: GetParametersUseCase,
    private val createAdsUseCase: CreateAdsUseCase
) : BaseViewModel<CreateAdsUiState, CreateAdsUiEvent>() {
    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess { categories ->
                    setState { currentState.copy(allCategories = categories.toImmutableList()) }
                }.onFailure { apiError ->
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    override fun createInitialState() = CreateAdsUiState()

    private fun step2Validate(): Boolean {
        return true
    }

    override fun onTriggerEvent(event: CreateAdsUiEvent) {
        when (event) {
            CreateAdsUiEvent.DismissDialog -> {
                setState {
                    copy(
                        showCategoryDialog = false,
                        imageIndexChooser = null,
                        showParameterDialog = null
                    )
                }
            }

            CreateAdsUiEvent.OnNext -> {
                when (currentState.screenStep) {
                    ScreenStep.Step1 -> {
                        if (step1Validate()) setState { copy(screenStep = ScreenStep.Step2) }
                    }

                    ScreenStep.Step2 -> {
                        if (step2Validate()) {
                            createAds()
                        }
                    }
                }
            }

            is CreateAdsUiEvent.OnSelectCategory -> {
                setState { copy(createAdsParam = createAdsParam.copy(category = event.category), showCategoryDialog = false) }
                getParameter()
            }

            CreateAdsUiEvent.ShowCategoryDialog -> {
                setState { copy(showCategoryDialog = true) }
            }

            is CreateAdsUiEvent.OnImageChooser -> {
                setState { copy(imageIndexChooser = event.index) }
            }

            is CreateAdsUiEvent.OmImagePicked -> {
                if (event.pathList.size == 1) {
                    setState {
                        copy(
                            createAdsParam = createAdsParam.copy(images = currentState.createAdsParam.images.mapIndexed { index, s ->
                                if (index == currentState.imageIndexChooser) event.pathList.first()
                                else s
                            }.toImmutableList())
                        )
                    }
                } else {
                    val temp = currentState.createAdsParam.images.toMutableList()
                    val filledIndexes: MutableList<Int> = mutableListOf()
                    event.pathList.forEachIndexed { _, path ->
                        temp.findIndex { it.isEmpty() }?.let {
                            filledIndexes.add(it)
                            temp[it] = path
                        } ?: run {
                            temp.forEachIndexed { index, s ->
                                if (index !in filledIndexes) {
                                    temp[index] = path
                                }
                            }
                        }
                    }
                    setState { copy(createAdsParam = createAdsParam.copy(images = temp.toImmutableList())) }
                }
            }

            is CreateAdsUiEvent.OnTitleChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(title = event.text)) }
            }

            is CreateAdsUiEvent.OnDescriptionChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(description = event.text)) }
            }

            is CreateAdsUiEvent.OnNeighborhood -> {

            }

            is CreateAdsUiEvent.OnPriceChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(price = event.text)) }
            }

            is CreateAdsUiEvent.OnParameter -> {
                when (event.parameter.dataType) {
                    DataType.CheckBoxInput -> {
                        setState {
                            copy(
                                parameters = parameters.map {
                                    if (it.id == event.parameter.id)
                                        it.copy(answer = it.name)
                                    else it
                                }.toImmutableList()
                            )
                        }
                    }

                    DataType.FixedOption -> {
                        setState { copy(showParameterDialog = event.parameter) }
                    }

                    else -> {
                        setState {
                            copy(
                                parameters = parameters.map {
                                    if (it.id == event.parameter.id)
                                        event.parameter
                                    else it
                                }.toImmutableList()
                            )
                        }
                    }
                }
            }

            is CreateAdsUiEvent.OnAnswerToParameter -> {
                setState {
                    copy(
                        showParameterDialog = null,
                        parameters = parameters.map {
                            if (it.id == event.parameter.id) event.parameter
                            else it
                        }.toImmutableList()
                    )
                }
            }
        }
    }

    private fun getParameter() {
        viewModelScope.launch {
            getParametersUseCase.invoke(currentState.createAdsParam.category!!.id).collect {
                it.onSuccess {
                    setState { copy(parameters = it.toImmutableList()) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun step1Validate(): Boolean {
        currentState.createAdsParam.let {
            if (it.category == null) {
                setUiMessage(UiMessage(intValue = com.kazemieh.ui.R.string.error_create_ads_select_category))
                return false
            } else if (it.title.isEmpty()) {
                setUiMessage(UiMessage(intValue = com.kazemieh.ui.R.string.error_create_ads_title))
                return false
            } else if (it.description.isEmpty()) {
                setUiMessage(UiMessage(intValue = com.kazemieh.ui.R.string.error_create_ads_description))
                return false
            }
        }
        return true
    }


    private fun createAds() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            createAdsUseCase.invoke(currentState.createAdsParam.copy(parameters = currentState.parameters)).collect {
                setState { copy(isLoading = false) }
                it.onSuccess {
                    setUiMessage(
                        UiMessage(
                            intValue = R.string.ads_created_successful,
                            messageType = MessageType.System,
                            status = MessageStatus.Success
                        )
                    )
                    delay(2 * 1000)
                    setState { copy(adsCreated = true, isLoading = false) }
                }.onFailure { apiError ->
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

}
