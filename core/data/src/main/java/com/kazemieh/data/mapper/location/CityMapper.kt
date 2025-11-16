package com.kazemieh.data.mapper.location

import com.kazemieh.domain.model.location.City
import com.kazemieh.network.dto.location.CityResponse

fun CityResponse.toDomain(): City {
    return City(id = id, name = name, neighborhoods = neighborhoods?.map { it.toDomain() })
}