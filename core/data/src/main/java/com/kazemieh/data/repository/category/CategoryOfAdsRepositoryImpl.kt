package com.kazemieh.data.repository.category

import com.kazemieh.data.mapper.category.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.repository.category.CategoryOfAdsRepository
import com.kazemieh.network.api.category.CategoryOfAdsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.onFailure
import kotlin.onSuccess

class CategoryOfAdsRepositoryImpl @Inject constructor(
    private val apiService: CategoryOfAdsApiService
) : CategoryOfAdsRepository {


    override suspend fun getCategoriesOfAds(searchText: String, cityId: Long): Flow<DataResult<List<CategoryOfAds>>> = flow{
        safeCall { apiService.getCategoriesOfAds(searchText, cityId) }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }
}