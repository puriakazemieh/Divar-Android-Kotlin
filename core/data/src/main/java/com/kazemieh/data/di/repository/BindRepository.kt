package com.kazemieh.data.di.repository

import com.kazemieh.data.repository.ads.AdsSummaryRepositoryImpl
import com.kazemieh.data.repository.category.CategoryOfAdsRepositoryImpl
import com.kazemieh.data.repository.category.CategoryRepositoryImpl
import com.kazemieh.data.repository.location.LocationRepositoryImpl
import com.kazemieh.data.repository.parameter.ParameterRepositoryImpl
import com.kazemieh.domain.repository.ads.AdsSummaryRepository
import com.kazemieh.domain.repository.category.CategoryOfAdsRepository
import com.kazemieh.domain.repository.category.CategoryRepository
import com.kazemieh.domain.repository.location.LocationRepository
import com.kazemieh.domain.repository.parameter.ParameterRepository
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

    @Binds
    fun bindLocation(repo: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindCategoryOfAds(repo: CategoryOfAdsRepositoryImpl): CategoryOfAdsRepository

    @Binds
    fun bindParameterRepository(repo: ParameterRepositoryImpl): ParameterRepository

}