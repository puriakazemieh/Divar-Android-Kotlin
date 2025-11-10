package com.kazemieh.network.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kazemieh.network.BuildConfig
import com.kazemieh.network.preferences.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EncryptSharedModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }


    @Provides
    @Singleton
    fun provideOkhttpClient(
        tokenPreferences: TokenPreferences
    ): OkHttpClient.Builder {
        val http = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        http.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", tokenPreferences.readToken())
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }).build()

        val logInterceptor = HttpLoggingInterceptor(logger = {
            if (BuildConfig.DEBUG)
                Log.d("949494", it)
        })

        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        http.addInterceptor(logInterceptor)

        return http
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient.Builder, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BaseUrl}/api/")
            .addConverterFactory(json.asConverterFactory("application/jscon".toMediaType()))
            .client(client.build())
            .build()
    }


}