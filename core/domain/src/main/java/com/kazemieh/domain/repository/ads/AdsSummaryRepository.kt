package com.kazemieh.domain.repository.ads

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ads.AdsSummary
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.model.paginate.Paging
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface AdsSummaryRepository {

    suspend fun getAdsSummary(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>>


}