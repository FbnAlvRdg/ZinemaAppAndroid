package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.api.AuthApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.RegisterRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val api : AuthApi) : AuthDataSource {
    override suspend fun register(registerRequestDTO: RegisterRequestDTO): UserResponseDTO {
        return api.register(registerRequestDTO)
    }

    override suspend fun login(loginRequestDTO: LoginRequestDTO): UserResponseDTO {
        return api.login(loginRequestDTO)
    }
}