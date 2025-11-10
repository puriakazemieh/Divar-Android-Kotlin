package com.kazemieh.domain.repository.category

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<DataResult<List<Category>>>

}