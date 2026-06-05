package com.example.proyecto_gestion_peliculas.data.remote.dto.auth

data class LoginResponseDTO(
    val token: String,
    val user: UserResponseDTO
)
