package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.LoginResponse

fun LoginResponseDTO.toDomain() : LoginResponse{
    return LoginResponse(
        token = token,
        user = user.toDomain()
    )
}