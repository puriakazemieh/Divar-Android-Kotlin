package com.kazemieh.data.repository.filter

import android.content.SharedPreferences
import com.kazemieh.domain.fake_data.fromJson
import com.kazemieh.domain.fake_data.toJson
import com.kazemieh.domain.model.filter.AdsFilter
import com.kazemieh.domain.repository.filter.FilterRepository
import com.kazemieh.secure_shared_pref.di.SharedPrefConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FilterRepository {
    override suspend fun saveFilterFromHome(adsFilter: AdsFilter?) {
        sharedPreferences.edit().putString(SharedPrefConstant.HOME_FILTER, adsFilter.toJson()).apply()
    }

    override suspend fun saveFilterFromCategory(adsFilter: AdsFilter?) {
        sharedPreferences.edit().putString(SharedPrefConstant.CATEGORY_FILTER, adsFilter.toJson()).apply()
    }

    override suspend fun readFilterFromHome(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstant.HOME_FILTER, null)?.fromJson<AdsFilter?>()?.let { filter ->
            emit(filter)
        } ?: run { emit(null) }
    }

    override suspend fun readFilterFromCategory(): Flow<AdsFilter?> = flow {
        sharedPreferences.getString(SharedPrefConstant.CATEGORY_FILTER, null)?.fromJson<AdsFilter?>()?.let { filter ->
            emit(filter)
        } ?: run { emit(null) }
    }

}