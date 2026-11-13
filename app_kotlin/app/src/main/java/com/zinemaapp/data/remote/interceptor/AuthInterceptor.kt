package com.zinemaapp.data.remote.interceptor

import android.content.Context
import com.zinemaapp.core.session.SessionManager
import com.zinemaapp.data.datastore.clearJwt
import com.zinemaapp.data.datastore.readJwt
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.pathSegments.firstOrNull() == "auth") {
            return chain.proceed(request)
        }

        val token = runBlocking {
            readJwt(context)
        }

        if (token.isBlank()) {
            return chain.proceed(request)
        }

        val authenticatedRequest =
            request.newBuilder().header("Authorization", "Bearer $token").build()

        val response = chain.proceed(authenticatedRequest)

        if (response.code == 401) {
            runBlocking {
                clearJwt(context)
            }

            SessionManager.notifySessionExpired()
        }

        return response
    }
}