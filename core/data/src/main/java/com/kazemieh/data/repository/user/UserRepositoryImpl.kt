package com.kazemieh.data.repository.user

import android.content.SharedPreferences
import com.kazemieh.data.mapper.user.toDomain
import com.kazemieh.data.utils.safeCall
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.onFailure
import com.kazemieh.domain.model.onSuccess
import com.kazemieh.domain.model.user.User
import com.kazemieh.domain.repository.user.UserRepository
import com.kazemieh.network.api.user.UserApiService
import com.kazemieh.network.dto.user.UserRequest
import com.kazemieh.secure_shared_pref.di.SharedPrefConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {

    override suspend fun login(mobile: String, password: String): Flow<DataResult<User>> = flow {
        safeCall { apiService.login(UserRequest(mobile, password)) }
            .onSuccess { data ->
                saveToken(data.token)
                emit(DataResult.Success(data.toDomain()))

            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun register(mobile: String, password: String, repeatPassword: String): Flow<DataResult<User>> = flow {
        safeCall {
            apiService.register(
                UserRequest(
                    mobile = mobile,
                    password = password,
                    repeatPassword = repeatPassword
                )
            )
        }
            .onSuccess { data ->
                saveToken(data.token)
                emit(DataResult.Success(data.toDomain()))

            }.onFailure {
                emit(DataResult.Failure(it))
            }
    }

    override suspend fun isLogin(): Flow<Boolean> = flow {
        emit(!sharedPreferences.getString(SharedPrefConstant.TOKEN, null).isNullOrEmpty())
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString(SharedPrefConstant.TOKEN, token).apply()
    }


}