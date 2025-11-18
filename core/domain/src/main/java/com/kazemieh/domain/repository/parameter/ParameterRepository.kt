package com.kazemieh.domain.repository.parameter

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.parameter.Parameter
import kotlinx.coroutines.flow.Flow

interface ParameterRepository {
    suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>>
}