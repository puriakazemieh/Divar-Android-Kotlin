package com.kazemieh.domain.repository.ads

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.domain.model.ads.CreateAdsParam
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>>

    suspend fun createAds(createAdsParam: CreateAdsParam): Flow<DataResult<Unit>>


}