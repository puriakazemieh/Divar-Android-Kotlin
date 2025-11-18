package com.kazemieh.domain.repository.category

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.category.CategoryOfAds
import kotlinx.coroutines.flow.Flow

interface CategoryOfAdsRepository {

    suspend fun getCategoriesOfAds(searchText: String, cityId: Long): Flow<DataResult<List<CategoryOfAds>>>


}