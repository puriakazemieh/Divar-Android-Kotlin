package com.kazemieh.create_ads

import androidx.compose.runtime.Stable
import com.kazemieh.domain.model.ads.CreateAdsParam
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.ui.extension.immutableListOf
import com.kazemieh.ui.viewmodel.UiEvent
import com.kazemieh.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CreateAdsUiState(
    val isLoading: Boolean = false,
    val adsCreated: Boolean = false,
    val screenStep: ScreenStep = ScreenStep.Step1,

    val showCategoryDialog: Boolean = false,
    val allCategories: ImmutableList<Category> = immutableListOf(),

    val toNeighborhood : Boolean = false,

    val createAdsParam: CreateAdsParam = CreateAdsParam(),

    val imageIndexChooser: Int? = null,
    val parameters: ImmutableList<Parameter> = immutableListOf(),
    val showParameterDialog: Parameter? = null,

    ) : UiState

enum class ScreenStep { Step1, Step2 }

sealed class CreateAdsUiEvent : UiEvent {
    data object OnNext : CreateAdsUiEvent()
    data object DismissDialog : CreateAdsUiEvent()
    data object ShowCategoryDialog : CreateAdsUiEvent()
    data class OnSelectCategory(val category: Category) : CreateAdsUiEvent()
    data class OnImageChooser(val index: Int) : CreateAdsUiEvent()
    data class OmImagePicked(val pathList: List<String>) : CreateAdsUiEvent()

    data class OnTitleChanged(val text: String) : CreateAdsUiEvent()
    data class OnDescriptionChanged(val text: String) : CreateAdsUiEvent()
    data object CheckNeighborhood : CreateAdsUiEvent()

    data object OnNeighborhood : CreateAdsUiEvent()
    data class OnPriceChanged(val text: String) : CreateAdsUiEvent()

    data class OnParameter(val parameter: Parameter) : CreateAdsUiEvent()
    data class OnAnswerToParameter(val parameter: Parameter) : CreateAdsUiEvent()

}

typealias OnAction = (CreateAdsUiEvent) -> Unit
