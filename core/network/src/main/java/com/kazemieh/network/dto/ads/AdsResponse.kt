package com.kazemieh.network.dto.ads

import com.kazemieh.network.dto.category.CategoryResponse
import com.kazemieh.network.dto.image.ImageResponse
import com.kazemieh.network.dto.location.NeighborhoodResponse
import com.kazemieh.network.dto.parameter.ParameterAnswerResponse
import com.kazemieh.network.dto.user.UserResponse
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class AdsResponse(
    val id: Long,

    val title: String,

    val description: String,

    val price: String,

    val neighborhood: NeighborhoodResponse,

    val user: UserResponse,

    val category: CategoryResponse,

    val images: List<ImageResponse>,

    val answers: List<ParameterAnswerResponse>,

    val createAt: String? = null,

    val updatedAt: String? = null,
)