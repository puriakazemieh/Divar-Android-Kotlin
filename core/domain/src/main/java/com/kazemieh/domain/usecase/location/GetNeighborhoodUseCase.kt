package com.kazemieh.domain.usecase.location

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.location.Neighborhood
import com.kazemieh.domain.repository.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(): Flow<DataResult<Neighborhood>> {
        return repo.getUserNeighborhood()
    }
}