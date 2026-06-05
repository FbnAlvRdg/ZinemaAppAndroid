package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.LoginResponse
import com.example.proyecto_gestion_peliculas.domain.model.User

interface AuthRepository {
    suspend fun register(username : String, email: String, password: String): User
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun me() : User
}