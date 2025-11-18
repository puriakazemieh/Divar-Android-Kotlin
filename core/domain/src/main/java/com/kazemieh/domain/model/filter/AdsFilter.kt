package com.kazemieh.domain.model.filter

import androidx.compose.runtime.Immutable
import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.location.Neighborhood
import com.kazemieh.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class AdsFilter(
    val category: Category? = null,
    val neighborhood: Neighborhood? = null,
    val price: String? = null,
    val parameters: List<Parameter>? = null,
    val searchText: String = "",
)
