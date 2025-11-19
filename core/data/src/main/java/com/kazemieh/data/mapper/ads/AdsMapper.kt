package com.kazemieh.data.mapper.ads

import com.kazemieh.data.mapper.category.toDomain
import com.kazemieh.data.mapper.image.toDomain
import com.kazemieh.data.mapper.location.toDomain
import com.kazemieh.data.mapper.parameter.toDomain
import com.kazemieh.data.mapper.user.toDomain
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.domain.model.ads.AdsSummary
import com.kazemieh.network.dto.ads.AdsResponse
import com.kazemieh.network.dto.ads.AdsSummaryResponse

fun AdsSummaryResponse.toDomain(): AdsSummary {
    return AdsSummary(
        id = id,
        title = title,
        price = price,
        neighborhood = neighborhood.toDomain(),
        previewImage = previewImage?.toDomain(),
        createAt = createAt
    )
}


fun AdsResponse.toDomain(): Ads {
    return Ads(
        id = id,
        title = title,
        description = description,
        price = price,
        neighborhood = neighborhood.toDomain(),
        user = user.toDomain(),
        category = category.toDomain(),
        images = images.map { it.toDomain() },
        answers = answers.map { it.toDomain() },
        createAt = createAt,
        updatedAt = updatedAt
    )
}