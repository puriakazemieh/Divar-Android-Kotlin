package com.kazemieh.domain.usecase

import com.kazemieh.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repo: CategoryRepository) {

    suspend operator fun invoke() = repo.getCategories()

}