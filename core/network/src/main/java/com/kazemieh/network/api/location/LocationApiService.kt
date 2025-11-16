package com.kazemieh.network.api.location


import com.kazemieh.network.dto.location.CityResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET

interface LocationApiService {

    @GET("v1/city")
    suspend fun getCities(): SuccessResponse<List<CityResponse>>

}