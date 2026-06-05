package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.RegisterRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(@Body user: RegisterRequestDTO): UserResponseDTO

    @POST("auth/login")
    suspend fun login(@Body login: LoginRequestDTO): LoginResponseDTO

    @GET("auth/me")
    suspend fun me() : UserResponseDTO
}