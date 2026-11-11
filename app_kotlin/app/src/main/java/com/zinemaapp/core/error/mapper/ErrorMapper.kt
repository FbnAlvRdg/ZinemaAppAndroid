package com.zinemaapp.core.error.mapper

import com.zinemaapp.core.error.Error
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toAppError(): Error {
    return when (this) {
        is IOException -> Error.CONNECTION_ERROR
        is HttpException -> when (code()) {
            400 -> Error.VALIDATION_ERROR
            401 -> Error.UNAUTHORIZED
            403 -> Error.FORBIDDEN
            404 -> Error.NOT_FOUND
            409 -> Error.CONFLICT
            in 500..599 -> Error.SERVER_ERROR
            else -> Error.UNKNOWN
        }

        else -> Error.UNKNOWN
    }
}