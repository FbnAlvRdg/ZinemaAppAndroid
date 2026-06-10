package com.example.proyecto_gestion_peliculas.data.remote.datasource.auth

import com.example.proyecto_gestion_peliculas.data.remote.api.AuthApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.RegisterRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val api : AuthApi) : AuthDataSource {
    override suspend fun register(registerRequestDTO: RegisterRequestDTO): UserResponseDTO {
        return api.register(registerRequestDTO)
    }

    override suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO {
        return api.login(loginRequestDTO)
    }

    override suspend fun me(): UserResponseDTO {
        return api.me()
    }
}