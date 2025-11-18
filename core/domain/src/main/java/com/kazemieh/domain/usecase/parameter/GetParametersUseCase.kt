package com.kazemieh.domain.usecase.parameter

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.domain.repository.parameter.ParameterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetParametersUseCase @Inject constructor(
    private val repo: ParameterRepository
) {
    suspend operator fun invoke(categoryId: Long): Flow<DataResult<List<Parameter>>> {
        return repo.getParameters(categoryId)
    }
}