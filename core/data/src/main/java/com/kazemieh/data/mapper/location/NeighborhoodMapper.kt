package com.kazemieh.data.mapper.location

import com.kazemieh.domain.model.location.Neighborhood
import com.kazemieh.network.dto.location.NeighborhoodResponse

fun NeighborhoodResponse.toDomain(): Neighborhood {
    return Neighborhood(id = id, name = name)
}