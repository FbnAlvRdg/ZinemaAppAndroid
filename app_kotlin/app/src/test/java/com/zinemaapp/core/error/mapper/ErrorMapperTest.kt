package com.zinemaapp.core.error.mapper

import com.zinemaapp.core.error.Error
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ErrorMapperTest {
    @Test
    fun `un error de conexion se convierte en CONNECTION_ERROR`() {
        val exception = IOException()

        val result = exception.toAppError()

        assertEquals(Error.CONNECTION_ERROR, result)
    }

    @Test
    fun `un error 400 se convierte en VALIDATION_ERROR`() {
        val response = Response.error<Any>(400, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.VALIDATION_ERROR, result)
    }

    @Test
    fun `un error 401 se convierte en UNAUTHORIZED`() {
        val response = Response.error<Any>(401, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.UNAUTHORIZED, result)
    }

    @Test
    fun `un error 403 se convierte en FORBIDDEN`() {
        val response = Response.error<Any>(403, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.FORBIDDEN, result)
    }

    @Test
    fun `un error 404 se convierte en NOT_FOUND`() {
        val response = Response.error<Any>(404, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.NOT_FOUND, result)
    }

    @Test
    fun `un error 409 se convierte en CONFLICT`() {
        val response = Response.error<Any>(409, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.CONFLICT, result)
    }

    @Test
    fun `un error 502 se convierte en SERVER_ERROR`() {
        val response = Response.error<Any>(502, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.SERVER_ERROR, result)
    }

    @Test
    fun `un error HTTP desconocido se convierte en UNKNOWN`() {
        val response = Response.error<Any>(418, "".toResponseBody(null))

        val exception = HttpException(response)

        val result = exception.toAppError()

        assertEquals(Error.UNKNOWN, result)
    }

    @Test
    fun `una excepcion desconocida se convierte en UNKNOWN`() {
        val exception = IllegalStateException()

        val result = exception.toAppError()

        assertEquals(Error.UNKNOWN, result)
    }
}