package com.example.proyecto_gestion_peliculas.domain.model

data class LoginResponse(
    val token : String,
    val user: User
)
