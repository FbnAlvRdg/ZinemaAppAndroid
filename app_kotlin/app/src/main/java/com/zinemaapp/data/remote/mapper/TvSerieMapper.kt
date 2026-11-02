package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.series.DetailsSerieDTO
import com.zinemaapp.data.remote.dto.series.TvSerieDTO
import com.zinemaapp.domain.model.TvSerie

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