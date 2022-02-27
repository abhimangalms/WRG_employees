package com.whiterabbit.wrgemployees.utils.network

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg)
            }
        }

        fun <T> create(exception: Throwable): ApiResponse<T> {
            return ApiErrorResponse(
                exception.message
            )
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val data: T?
) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val errorMessage: String?
) : ApiResponse<T>()
