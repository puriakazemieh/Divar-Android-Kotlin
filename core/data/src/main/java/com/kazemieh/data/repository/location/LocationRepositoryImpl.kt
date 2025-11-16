package com.kazemieh.data.repository.location

import android.content.SharedPreferences
import android.util.Log
import com.kazemieh.data.mapper.location.toDomain
import com.kazemieh.data.utils.fromJson
import com.kazemieh.data.utils.safeCall
import com.kazemieh.data.utils.toJson
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.NotFoundError
import com.kazemieh.domain.model.location.City
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.repository.location.LocationRepository
import com.kazemieh.network.api.location.LocationApiService
import com.kazemieh.secure_shared_pref.di.SharedPrefConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.collections.map

class LocationRepositoryImpl @Inject constructor(
    private val apiService: LocationApiService,
    private val sharedPreferences: SharedPreferences
) : LocationRepository {
    override suspend fun getCities(): Flow<DataResult<List<City>>> = flow {
        safeCall { apiService.getCities() }
            .onSuccess { data ->
                emit(DataResult.Success(data.map { it.toDomain() }))
            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun saveCity(city: City) {
        city.toJson()?.let {
            sharedPreferences.edit().putString(SharedPrefConstant.USER_CITY, it).apply()
        }
    }

    override suspend fun getUserCity(): Flow<DataResult<City>> = flow {
        sharedPreferences.getString(SharedPrefConstant.USER_CITY, null)?.fromJson<City?>()?.let {
            emit(DataResult.Success(it))
        } ?: run {
            emit(DataResult.Failure(NotFoundError(404)))
        }
    }

}