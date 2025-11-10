package com.kazemieh.network.dto.ads

import com.kazemieh.network.dto.image.ImageResponse
import com.kazemieh.network.dto.neighborhood.NeighborhoodResponse
import kotlinx.serialization.Serializable

@Serializable
data class AdsSummaryResponse(
    val id: Long,
    val title: String,
    val price: String,
    val neighborhood: NeighborhoodResponse,
    val previewImage: ImageResponse?,
    val createAt: String? = null,
)
