package com.kazemieh.network.dto.location

import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val id: Long,
    val name: String,
    val neighborhoods: List<NeighborhoodResponse>? = null
)
