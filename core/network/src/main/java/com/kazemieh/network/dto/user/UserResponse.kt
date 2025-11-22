package com.kazemieh.network.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val name: String,
    val family: String,
    val email: String,
    val token: String,
    val mobile: String,
    val createdAt: String?,
    val updatedAt: String?,
)
