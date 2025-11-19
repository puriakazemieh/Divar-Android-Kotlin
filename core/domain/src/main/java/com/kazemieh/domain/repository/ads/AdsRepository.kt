package com.kazemieh.domain.repository.ads

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ads.Ads
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAdsDetail(
        id: Long
    ): Flow<DataResult<Ads>>

}