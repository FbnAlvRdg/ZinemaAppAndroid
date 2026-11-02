package com.zinemaapp.data.remote.api

import com.zinemaapp.data.remote.dto.auth.LoginRequestDTO
import com.zinemaapp.data.remote.dto.auth.LoginResponseDTO
import com.zinemaapp.data.remote.dto.auth.RegisterRequestDTO
import com.zinemaapp.data.remote.dto.auth.UserResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(@Body user: RegisterRequestDTO): UserResponseDTO

    @POST("auth/login")
    suspend fun login(@Body login: LoginRequestDTO): LoginResponseDTO
}