package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.film.DetailsFilmDTO
import com.zinemaapp.data.remote.dto.film.FilmDTO
import com.zinemaapp.domain.model.Actor
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.model.Genre
import java.time.LocalDate
import kotlin.collections.map


fun FilmDTO.toDomain(): Film {
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
                id = it.id,
                name = it.name,
                character = it.character
            )
        })
}


