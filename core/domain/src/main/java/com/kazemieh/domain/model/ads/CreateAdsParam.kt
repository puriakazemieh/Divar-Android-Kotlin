package com.kazemieh.domain.model.ads

import com.kazemieh.domain.model.category.Category
import com.kazemieh.domain.model.location.Neighborhood
import com.kazemieh.domain.model.parameter.Parameter
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable

@Serializable
data class CreateAdsParam(
    val category: Category? = null,
    val neighborhood: Neighborhood? = null,
    val images: ImmutableList<String> = listOf("", "", "", "", "", "").toImmutableList(),
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val parameters: ImmutableList<Parameter> = listOf<Parameter>().toImmutableList()
)
