package com.kazemieh.domain.usecase.user

import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.parameter.Parameter
import com.kazemieh.domain.model.user.User
import com.kazemieh.domain.repository.parameter.ParameterRepository
import com.kazemieh.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(mobile: String, password: String, repeatPassword: String): Flow<DataResult<User>> {
        return repo.register(mobile, password, repeatPassword)
    }
}