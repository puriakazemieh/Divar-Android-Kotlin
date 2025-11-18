package com.kazemieh.network.api.parameter

import com.kazemieh.network.dto.parameter.ParameterResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ParameterApiService {

    @GET("v1/parameter")
    suspend fun getParameters(
        @Query("categoryId") categoryId: Long = 0,
    ): SuccessResponse<List<ParameterResponse>>

}