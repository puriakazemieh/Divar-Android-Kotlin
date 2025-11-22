package com.kazemieh.domain.usecase.location

import com.kazemieh.domain.model.location.Neighborhood
import com.kazemieh.domain.repository.location.LocationRepository
import javax.inject.Inject

class SaveNeighborhoodUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(neighborhood: Neighborhood) {
        return repo.saveNeighborhood(neighborhood)
    }
}