package com.kazemieh.data.mapper.category

import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.network.dto.category.CategoryOfAdsResponse

fun CategoryOfAdsResponse.toDomain(): CategoryOfAds {
    return CategoryOfAds(
        categoryName = categoryName,
        categoryId = categoryId,
        adsCount = adsCount,
        adsTitle = adsTitle
    )
}