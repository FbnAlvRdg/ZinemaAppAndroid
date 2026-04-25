package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.FilmDTO
import com.example.proyecto_gestion_peliculas.domain.Film
import java.time.LocalDate

fun FilmDTO.toDomain(): Film {
    return Film(
        id = id,
        title = title,
        originalTitle = originalTitle,
        releaseDate = LocalDate.parse(releaseDate),
        genres = emptyList(),
        director = null,
        actors = emptyList(),
        synopsis = synopsis,
        poster = poster,
        rating = rating
    )
}