package com.kazemieh.domain.usecase.location

import com.kazemieh.domain.model.location.City
import com.kazemieh.domain.repository.location.LocationRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val repo: LocationRepository
) {
    suspend operator fun invoke(city: City) {
        return repo.saveCity(city)
    }
}