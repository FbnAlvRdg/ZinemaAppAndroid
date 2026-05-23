package com.example.proyecto_gestion_peliculas.domain.usecase.auth

import com.example.proyecto_gestion_peliculas.domain.model.User
import com.example.proyecto_gestion_peliculas.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository : AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String) : User {
        return repository.register(username, email, password)
    }
}