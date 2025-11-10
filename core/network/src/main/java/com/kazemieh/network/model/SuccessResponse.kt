package com.kazemieh.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    val status: Status = Status.SUCCESS,
    val data: T?,
    val message: String = ""
)
