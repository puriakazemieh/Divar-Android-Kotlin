package com.kazemieh.network.api.category

import com.kazemieh.network.dto.category.CategoryResponse
import com.kazemieh.network.model.SuccessResponse
import retrofit2.http.GET

interface CategoryApiService {

    @GET("v1/category")
    suspend fun getCategories(): SuccessResponse<List<CategoryResponse>>

}