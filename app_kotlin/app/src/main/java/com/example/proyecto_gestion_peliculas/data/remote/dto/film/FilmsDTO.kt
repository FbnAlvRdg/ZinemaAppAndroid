package com.example.proyecto_gestion_peliculas.data.remote.dto.film

data class FilmsDTO(
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val releaseDate: String?,
    val synopsis: String?,
    val poster: String?,
    val rating: Double?,
)