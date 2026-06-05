package com.example.proyecto_gestion_peliculas.domain.usecase.auth

import com.example.proyecto_gestion_peliculas.domain.model.LoginResponse
import com.example.proyecto_gestion_peliculas.domain.model.User
import com.example.proyecto_gestion_peliculas.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email : String, password : String) : LoginResponse {
        return repository.login(email, password)
    }
}