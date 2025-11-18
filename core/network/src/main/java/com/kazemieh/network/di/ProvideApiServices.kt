package com.kazemieh.network.di

import com.kazemieh.network.api.ads.AdsSummaryApiService
import com.kazemieh.network.api.category.CategoryApiService
import com.kazemieh.network.api.category.CategoryOfAdsApiService
import com.kazemieh.network.api.location.LocationApiService
import com.kazemieh.network.api.parameter.ParameterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProvideApiServices {

    @Singleton
    @Provides
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService {
        return retrofit.create(CategoryApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAdsSummaryApiService(retrofit: Retrofit): AdsSummaryApiService {
        return retrofit.create(AdsSummaryApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationApiService(retrofit: Retrofit): LocationApiService {
        return retrofit.create(LocationApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryOfAdsApiService(retrofit: Retrofit): CategoryOfAdsApiService {
        return retrofit.create(CategoryOfAdsApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideParametersApiService(retrofit: Retrofit): ParameterApiService {
        return retrofit.create(ParameterApiService::class.java)
    }
}