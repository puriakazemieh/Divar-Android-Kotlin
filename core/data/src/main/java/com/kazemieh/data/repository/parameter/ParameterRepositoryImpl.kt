package com.kazemieh.data.repository.parameter

import android.content.SharedPreferences
import com.kazemieh.data.mapper.parameter.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.domain.repository.parameter.ParameterRepository
import com.kazemieh.network.api.parameter.ParameterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ParameterRepositoryImpl @Inject constructor(
    private val apiService: ParameterApiService,
    private val sharedPreferences: SharedPreferences
) : ParameterRepository {
    override suspend fun getParameters(categoryId: Long): Flow<DataResult<List<Parameter>>> = flow {
        safeCall { apiService.getParameters(categoryId) }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }
}