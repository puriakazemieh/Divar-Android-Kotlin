package com.kazemieh.domain.usecase.ads

import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.repository.ads.AdsSummaryRepository
import javax.inject.Inject

class GetAdsSummaryUseCase @Inject constructor(
    private val repo: AdsSummaryRepository
) {
    suspend operator fun invoke(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ) = repo.getAdsSummary(
        page = page,
        cityId = cityId,
        adsFilter = adsFilter
    )
}