package com.kazemieh.data.utils

import android.util.Log
import com.kazemieh.domain.model.ApiError
import com.kazemieh.domain.model.DataResult
import com.kazemieh.domain.model.ServerError
import com.kazemieh.network.model.FailureResponse
import com.kazemieh.network.model.Status
import com.kazemieh.network.model.SuccessResponse
import kotlinx.serialization.json.Json

suspend fun <T> safeCall(execute: suspend () -> SuccessResponse<T>): DataResult<T> {
    return try {
        val response = execute()
        if (response.status == Status.SUCCESS) {
            DataResult.Success(response.data!!, response.message)
        } else {
            Log.d("94949494", " else ")
            DataResult.Failure(ServerError(504))
        }
    } catch (e: Throwable) {
        Log.d("94949494", " $e ")
        DataResult.Failure(getApiError(e))
    }
}


fun getApiError(throwable: Throwable): ApiError {
    when (throwable) {
        is retrofit2.HttpException -> {
            if (throwable.code() == 500) {
                return ServerError(500, message = throwable.message())
            }else if (throwable.code() == 404)
                return ServerError(404, message = throwable.message())

            val bodyError = throwable.response()?.errorBody()?.string() ?: ""
            val failureResponse = Json.decodeFromString<FailureResponse>(bodyError)
            return failureResponse.toApiError()
        }

        else -> {
            return object : ApiError {
                override val httpStatus: Int = 598
                override val message: String = throwable.message ?: "An unknown error occurred."
            }
        }
    }
}
