package com.kazemieh.domain.usecase.filter

import com.kazemieh.domain.repository.filter.FilterRepository
import javax.inject.Inject

class ReadFilterFromHomeUseCase @Inject constructor(
    private val repo: FilterRepository
) {
    suspend operator fun invoke() = repo.readFilterFromHome()
}