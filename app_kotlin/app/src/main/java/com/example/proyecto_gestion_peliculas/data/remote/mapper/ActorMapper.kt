package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.ActorDTO
import com.example.proyecto_gestion_peliculas.domain.model.Actor

fun ActorDTO.toDomain(): Actor {
    return Actor(
        id = id,
        name = name,
        character = character
    )
}