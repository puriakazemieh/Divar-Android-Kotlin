package com.kazemieh.domain.usecase.ads

import com.kazemieh.domain.repository.ads.AdsRepository
import javax.inject.Inject

class GetAdsDetailUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        id: Long
    ) = repo.getAdsDetail(
        id = id
    )
}