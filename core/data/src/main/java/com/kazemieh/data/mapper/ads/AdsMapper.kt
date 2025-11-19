package com.kazemieh.data.mapper.ads

import com.kazemieh.data.mapper.category.toDomain
import com.kazemieh.data.mapper.image.toDomain
import com.kazemieh.data.mapper.location.toDomain
import com.kazemieh.data.mapper.parameter.toDomain
import com.kazemieh.data.mapper.user.toDomain
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.domain.model.ads.AdsSummary
import com.kazemieh.domain.model.ads.CreateAdsParam
import com.kazemieh.network.dto.ads.AdsResponse
import com.kazemieh.network.dto.ads.AdsSummaryResponse
import com.kazemieh.network.dto.ads.CreateAdsRequest
import com.kazemieh.network.dto.ads.ParameterAnswerRequest
import kotlin.collections.map

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


fun CreateAdsParam.toRequest(): CreateAdsRequest {
    return CreateAdsRequest(
        id = null,
        title = title,
        description = description,
        price = price,
        neighborhoodId = 1,
        categoryId = category!!.id,
        answers = parameters.map {
            ParameterAnswerRequest(
                answer = it.answer.toString(),
                parameterId = it.id
            )
        }
    )
}