package com.kazemieh.domain.usecase.user

import com.kazemieh.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoginUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repo.isLogin()
    }
}