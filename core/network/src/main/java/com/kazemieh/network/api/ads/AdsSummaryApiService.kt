package com.kazemieh.network.api.ads

import com.kazemieh.network.dto.ads.AdsSummaryResponse
import com.kazemieh.network.dto.paginate.PagingResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsSummaryApiService {

    @GET("v1/ads")
    suspend fun getAdsSummary(
        @Query("page") page: Int = 0
    ): SuccessResponse<PagingResponse<List<AdsSummaryResponse>>>

}