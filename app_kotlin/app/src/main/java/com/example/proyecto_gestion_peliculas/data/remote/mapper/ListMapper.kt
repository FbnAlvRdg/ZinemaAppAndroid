package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.ListResponse

fun ListResponseDTO.toDomain() : ListResponse{
    return ListResponse(
        id = id,
        name = name
    )
}