package com.kazemieh.network.api.ads

import com.kazemieh.network.dto.ads.AdsSummaryResponse
import com.kazemieh.network.dto.ads.GetAdsRequest
import com.kazemieh.network.dto.paginate.PagingResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AdsSummaryApiService {

    @POST("v1/ads/filter")
    suspend fun getAdsSummary(
        @Body getAdsRequest: GetAdsRequest
    ): SuccessResponse<PagingResponse<List<AdsSummaryResponse>>>

}