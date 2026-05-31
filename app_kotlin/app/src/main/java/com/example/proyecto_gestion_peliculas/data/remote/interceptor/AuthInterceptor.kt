package com.example.proyecto_gestion_peliculas.data.remote.interceptor

import android.content.Context
import com.example.proyecto_gestion_peliculas.data.datastore.readJwt
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = runBlocking {
            readJwt(context)
        }

        val request =
            originalRequest.newBuilder().addHeader("Authorization", "Bearer $token").build()

        return chain.proceed(request)
    }
}