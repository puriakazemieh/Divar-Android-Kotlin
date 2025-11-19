package com.kazemieh.domain.usecase.ads

import com.kazemieh.domain.model.ads.CreateAdsParam
import com.kazemieh.domain.repository.ads.AdsRepository
import javax.inject.Inject

class CreateAdsUseCase @Inject constructor(
    private val repo: AdsRepository
) {
    suspend operator fun invoke(
        createAdsParam: CreateAdsParam
    ) = repo.createAds(
        createAdsParam = createAdsParam
    )
}