package com.kazemieh.network.model

data class SuccessResponse<T>(
    val status: Status = Status.Success,
    val data: T?,
    val message: String = ""
)
