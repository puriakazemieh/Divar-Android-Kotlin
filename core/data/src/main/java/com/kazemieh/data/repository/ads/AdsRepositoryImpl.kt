package com.kazemieh.data.repository.ads

import com.kazemieh.data.mapper.ads.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ads.Ads
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.repository.ads.AdsRepository
import com.kazemieh.network.api.ads.AdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AdsRepositoryImpl @Inject constructor(
    private val apiService: AdsApiService
) : AdsRepository {
    override suspend fun getAdsDetail(id: Long): Flow<DataResult<Ads>> = flow {
        safeCall {
            apiService.getAdsDetail(id)
        }.onSuccess { data ->
            emit(DataResult.Success(data.toDomain()))
        }.onFailure {
            emit(DataResult.Failure(it))
        }
    }


}