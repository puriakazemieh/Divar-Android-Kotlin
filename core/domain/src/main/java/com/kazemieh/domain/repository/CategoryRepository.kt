package com.kazemieh.domain.repository

import com.kazemieh.domain.model.Category
import com.kazemieh.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<DataResult<List<Category>>>

}