package com.kazemieh.data.mapper.category

import com.kazemieh.data.BuildConfig
import com.kazemieh.domain.model.category.Category
import com.kazemieh.network.dto.category.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = "${BuildConfig.BaseUrl}/$icon",
        children = children?.map { it.toDomain() }?:emptyList()
    )
}