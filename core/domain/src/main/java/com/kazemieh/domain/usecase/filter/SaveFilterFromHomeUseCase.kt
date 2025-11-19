package com.kazemieh.domain.usecase.filter

import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.repository.filter.FilterRepository
import javax.inject.Inject

class SaveFilterFromHomeUseCase @Inject constructor(
    private val repo: FilterRepository
) {
    suspend operator fun invoke(adsFilter: AdsFilter? ) = repo.saveFilterFromHome(adsFilter)
}