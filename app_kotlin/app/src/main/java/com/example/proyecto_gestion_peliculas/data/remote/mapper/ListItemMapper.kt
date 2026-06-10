package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListItemResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.ListItemResponse

fun ListItemResponseDTO.toDomain(): ListItemResponse {
    return ListItemResponse(
        id = id,
        tmdbId = tmdbId,
        type = type
    )
}