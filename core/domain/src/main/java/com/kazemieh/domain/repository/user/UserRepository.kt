package com.kazemieh.domain.repository.user

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(
        mobile: String,
        password: String,
    ): Flow<DataResult<User>>

    suspend fun register(
        mobile: String,
        password: String,
        repeatPassword: String,
    ): Flow<DataResult<User>>

    suspend fun isLogin(): Flow<Boolean>
}