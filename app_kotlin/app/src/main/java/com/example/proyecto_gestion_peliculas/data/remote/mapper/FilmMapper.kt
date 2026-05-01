package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.FilmDTO
import com.example.proyecto_gestion_peliculas.domain.model.Film
import java.time.LocalDate

fun FilmDTO.toDomain(): Film {
    return Film(
        id = id,
        title = title ?: "",
        originalTitle = originalTitle ?: "",
        releaseDate = releaseDate?.takeIf { releaseDate.isNotBlank() } ?.let { runCatching { LocalDate.parse(releaseDate) }.getOrNull() },
        genres = emptyList(),
        director = null,
        actors = emptyList(),
        synopsis = synopsis ?: "",
        poster = poster?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        rating = rating ?: 0.0
    )
}