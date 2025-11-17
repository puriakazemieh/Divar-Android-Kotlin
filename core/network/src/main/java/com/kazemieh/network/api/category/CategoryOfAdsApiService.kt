package com.kazemieh.network.api.category

import com.kazemieh.network.dto.category.CategoryOfAdsResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryOfAdsApiService {

    @GET("v1/ads/categories_of_ads")
    suspend fun getCategoriesOfAds(
        @Query("searchText") searchText: String
    ): SuccessResponse<List<CategoryOfAdsResponse>>

}