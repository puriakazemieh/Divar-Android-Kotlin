package com.kazemieh.domain.usecase.category

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.category.CategoryOfAds
import com.kazemieh.domain.repository.category.CategoryOfAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesOfAdsUseCase @Inject constructor(
    private val repo: CategoryOfAdsRepository
) {
    suspend operator fun invoke(searchText: String): Flow<DataResult<List<CategoryOfAds>>> {
        return repo.getCategoriesOfAds(searchText)
    }
}