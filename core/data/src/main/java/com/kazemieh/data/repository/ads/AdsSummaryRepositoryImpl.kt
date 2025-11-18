package com.kazemieh.data.repository.ads

import com.kazemieh.data.mapper.ads.toDomain
import com.kazemieh.data.mapper.ads.toRequest
import com.kazemieh.data.mapper.paginate.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ads.AdsSummary
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.model.paginate.Paging
import com.kazemieh.domain.repository.ads.AdsSummaryRepository
import com.kazemieh.network.api.ads.AdsSummaryApiService
import com.kazemieh.network.dto.ads.GetAdsRequest
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.onFailure
import kotlin.onSuccess

class AdsSummaryRepositoryImpl @Inject constructor(
    private val apiService: AdsSummaryApiService
) : AdsSummaryRepository {

    override suspend fun getAdsSummary(
        adsFilter: AdsFilter,
        page: Int,
        cityId: Long,
    ): Flow<DataResult<Paging<ImmutableList<AdsSummary>>>> = flow {
        safeCall {
            apiService.getAdsSummary(
                GetAdsRequest(
                    categoryId = null,
                    neighborhoodId = null,
                    cityId = cityId,
                    price = null,
                    parameters = null,
                    searchText = "",
                    page = page
                )
//                adsFilter.toRequest(cityId = cityId, page = page)
            )
        }.onSuccess { data ->
            val paging = data.toDomain(
                contentMapper = { it.map { adsSummaryResponse -> adsSummaryResponse.toDomain() }.toImmutableList() }
            )
            emit(DataResult.Success(paging))
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }

}