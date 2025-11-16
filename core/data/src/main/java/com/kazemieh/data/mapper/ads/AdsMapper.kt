package com.kazemieh.data.mapper.ads

import com.kazemieh.data.mapper.image.toDomain
import com.kazemieh.data.mapper.location.toDomain
import com.kazemieh.domain.model.ads.AdsSummary
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