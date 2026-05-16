package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.GenreDTO
import com.example.proyecto_gestion_peliculas.domain.model.Genre

fun GenreDTO.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}