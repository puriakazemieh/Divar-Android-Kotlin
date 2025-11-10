package com.kazemieh.domain.usecase.category

import com.kazemieh.domain.repository.category.CategoryRepository
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.category.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repo: CategoryRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<Category>>> {
        return repo.getCategories()
    }
}