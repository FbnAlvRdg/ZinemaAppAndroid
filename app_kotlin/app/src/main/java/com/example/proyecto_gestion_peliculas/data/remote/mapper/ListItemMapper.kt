package com.example.proyecto_gestion_peliculas.data.remote.mapper

import androidx.compose.ui.text.toLowerCase
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.ListItemResponseDTO
import com.example.proyecto_gestion_peliculas.domain.model.ListItemResponse
import java.util.Locale
import java.util.Locale.getDefault

fun ListItemResponseDTO.toDomain(): ListItemResponse {
    return ListItemResponse(
        id = id,
        tmdbId = tmdbId,
        type = type.lowercase(getDefault()),
        title = title,
        poster = poster
    )
}