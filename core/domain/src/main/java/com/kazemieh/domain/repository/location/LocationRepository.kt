package com.kazemieh.domain.repository.location

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.location.City
import com.kazemieh.domain.model.location.Neighborhood
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCities(): Flow<DataResult<List<City>>>

    suspend fun saveCity(city: City): Unit

    suspend fun getUserCity(): Flow<DataResult<City>>

    suspend fun getCitiesWidthNeighborhoods(): Flow<DataResult<List<City>>>

    suspend fun saveNeighborhood(neighborhood: Neighborhood): Unit

    suspend fun getUserNeighborhood(): Flow<DataResult<Neighborhood>>

}