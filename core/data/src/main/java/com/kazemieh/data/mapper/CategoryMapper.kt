package com.kazemieh.data.mapper

import com.divar.network.dto.CategoryResponse
import com.kazemieh.domain.model.Category

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = "http://192.168.69.157:8080/$icon",
        children = children.map { it.toDomain() }
    )
}