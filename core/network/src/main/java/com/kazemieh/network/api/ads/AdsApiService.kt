package com.kazemieh.network.api.ads

import com.kazemieh.network.dto.ads.AdsResponse
import com.kazemieh.network.dto.paginate.PagingResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsApiService {

    @GET("v1/ads/detail")
    suspend fun getAdsDetail(
        @Query("id") id: Long
    ): SuccessResponse<AdsResponse>

}