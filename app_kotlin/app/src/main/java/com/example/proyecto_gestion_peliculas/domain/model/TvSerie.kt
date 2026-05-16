package com.example.proyecto_gestion_peliculas.domain.model

data class TvSerie(
    val id: Int,
    val name : String?,
    val originCountry: List<String?>,
    val overview: String?,
    val poster: String?,
    val rating: Double?,
    val firstAireDate: String?,
    val genres: List<Genre>,
    val actors: List<Actor>
)
