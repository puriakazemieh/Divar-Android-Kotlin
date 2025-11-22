package com.kazemieh.data.mapper.user

import com.kazemieh.domain.model.user.User
import com.kazemieh.network.dto.user.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        name = name,
        family = family,
        email = email,
        token = token,
        mobile = mobile,
        createAt = createdAt,
        updatedAt = updatedAt
    )
}