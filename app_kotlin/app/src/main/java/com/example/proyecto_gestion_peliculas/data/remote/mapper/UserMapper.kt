package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.UserResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.User

fun UserResponseDTO.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email
    )
}