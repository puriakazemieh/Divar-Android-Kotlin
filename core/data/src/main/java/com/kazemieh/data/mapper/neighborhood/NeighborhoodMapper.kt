package com.kazemieh.data.mapper.neighborhood

import com.kazemieh.domain.model.neighborhood.Neighborhood
import com.kazemieh.network.dto.neighborhood.NeighborhoodResponse

fun NeighborhoodResponse.toDomain(): Neighborhood {
    return Neighborhood(id = id, name = name)
}