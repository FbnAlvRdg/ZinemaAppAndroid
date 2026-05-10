package com.example.proyecto_gestion_peliculas.data.remote.mapper

import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie

fun TvSerieDTO.toDomain(): TvSerie {
    return TvSerie(
        id = id,
        name = name,
        originCountry = originCountry,
        overview = overview,
        poster = poster,
        rating = rating,
        firstAireDate = firstAireDate,
        genres = emptyList(),
        actors = emptyList()
    )

}