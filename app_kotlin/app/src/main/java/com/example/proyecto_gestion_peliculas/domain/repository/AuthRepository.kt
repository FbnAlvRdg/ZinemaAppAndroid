package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.domain.model.User

interface AuthRepository {
    suspend fun register(username : String, email: String, password: String): User
    suspend fun login(email: String, password: String): User
}