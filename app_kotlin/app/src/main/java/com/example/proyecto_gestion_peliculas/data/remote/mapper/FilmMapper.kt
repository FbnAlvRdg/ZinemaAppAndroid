package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.PopularFilmsDTO
import com.example.proyecto_gestion_peliculas.domain.model.Actor
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import java.time.LocalDate

fun PopularFilmsDTO.toDomain(): Film {
    return Film(
        id = id,
        title = title ?: "",
        originalTitle = originalTitle ?: "",
        releaseDate = releaseDate?.takeIf { releaseDate.isNotBlank() }
            ?.let { runCatching { LocalDate.parse(releaseDate) }.getOrNull() },
        genres = emptyList(),
        director = null,
        actors = emptyList(),
        synopsis = synopsis ?: "",
        poster = poster?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        rating = rating ?: 0.0
    )
}

fun DetailsFilmDTO.toDomain(): Film {

    return Film(
        id = id,
        title = title ?: "",
        originalTitle = originalTitle ?: "",
        releaseDate = releaseDate
            ?.takeIf { it.isNotBlank() }
            ?.let { runCatching { LocalDate.parse(it) }.getOrNull() },
        synopsis = synopsis ?: "",
        poster = poster?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        rating = rating ?: 0.0,
        genres = genres?.map {
            it.let { Genre(it.id, it.name) }
        } ?: emptyList(),
        director = director ?: "",
        actors = actors.take(5).map {
            Actor(
                name = it.name,
                character = it.character
            )
        })
}


