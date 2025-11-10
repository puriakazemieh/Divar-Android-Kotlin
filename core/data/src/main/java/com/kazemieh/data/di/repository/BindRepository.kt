package com.kazemieh.data.di.repository

import com.kazemieh.data.repository.ads.AdsSummaryRepositoryImpl
import com.kazemieh.data.repository.category.CategoryRepositoryImpl
import com.kazemieh.domain.repository.ads.AdsSummaryRepository
import com.kazemieh.domain.repository.category.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindRepository {

    @Binds
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindAdsSummary(repo: AdsSummaryRepositoryImpl): AdsSummaryRepository
}