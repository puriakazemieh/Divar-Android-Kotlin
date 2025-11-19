package com.kazemieh.domain.model.filter

import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
sealed class FilterClickType {
    @Serializable
    data object OnFilter : FilterClickType()
    data class OnCategory(val isRemove: Boolean) : FilterClickType()
    data class OnNeighborhood(val isRemove: Boolean) : FilterClickType()
    @Serializable
    data class OnPrice(val isRemove: Boolean) : FilterClickType()
    data class OnCategoryToShowAds(val category: Category) : FilterClickType()
    data class OnParameter(val parameter: Parameter, val isRemove: Boolean) : FilterClickType()
}
