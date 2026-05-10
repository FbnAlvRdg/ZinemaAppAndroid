package com.example.proyecto_gestion_peliculas.data.remote.dto.series

data class TvSerieDTO(
    val id: Int,
    val name : String?,
    val originCountry: List<String?>,
    val overview: String?,
    val poster: String?,
    val rating: Double?,
    val firstAireDate: String?
)
