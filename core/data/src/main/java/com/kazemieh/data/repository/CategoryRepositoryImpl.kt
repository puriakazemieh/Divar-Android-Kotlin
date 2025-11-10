package com.kazemieh.data.repository

import com.kazemieh.network.api.CategoryApiService
import com.kazemieh.data.mapper.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.Category
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: CategoryApiService
) : CategoryRepository {
    override suspend fun getCategories(): Flow<DataResult<List<Category>>> = flow {
        safeCall { apiService.getCategories() }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

}