package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.RegisterRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO

interface AuthDataSource {
    suspend fun register(registerRequestDTO: RegisterRequestDTO) : UserResponseDTO
    suspend fun login(loginRequestDTO: LoginRequestDTO) : LoginResponseDTO
    suspend fun me() : UserResponseDTO
}