package com.kazemieh.network.dto.location

import kotlinx.serialization.Serializable

@Serializable
data class NeighborhoodResponse(
    val id: Long,
    val name: String,
)
