package com.kazemieh.domain.usecase.ads

import com.kazemieh.domain.repository.ads.AdsSummaryRepository
import javax.inject.Inject

class GetAdsSummaryUseCase @Inject constructor(
    private val repo: AdsSummaryRepository
) {
    suspend operator fun invoke(page: Int) = repo.getAdsSummary(page = page)
}