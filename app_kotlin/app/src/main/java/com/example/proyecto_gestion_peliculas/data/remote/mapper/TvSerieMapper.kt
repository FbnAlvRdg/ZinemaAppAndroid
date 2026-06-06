package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.series.DetailsSerieDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO
import com.example.proyecto_gestion_peliculas.domain.model.Actor
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import java.time.format.DateTimeFormatter

fun TvSerieDTO.toDomain(): TvSerie {
    return TvSerie(
        id = id,
        name = name,
        originCountry = originCountry,
        overview = overview,
        poster = poster,
        rating = rating,
        firstAireDate = firstAireDate,
        genres = genres.filterNotNull().map { genreDTO -> genreDTO.toDomain() },
        actors = actors.filterNotNull().map { actorDTO -> actorDTO.toDomain() }
    )
}

fun DetailsSerieDTO.toDomain(): TvSerie {
    return TvSerie(
        id = id,
        name = name ?: "",
        originCountry = originCountry,
        firstAireDate = firstAireDate.toString(),
        overview = overview ?: "",
        poster = poster?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        rating = rating ?: 0.0,
        actors = actors.take(5).map { actorDTO ->
            actorDTO.toDomain()
        },
        genres = genres.map { genreDTO ->
            genreDTO.toDomain()
        }
    )
}