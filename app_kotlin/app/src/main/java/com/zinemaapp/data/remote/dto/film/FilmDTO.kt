package com.zinemaapp.data.remote.dto.film

data class FilmDTO(
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val releaseDate: String?,
    val synopsis: String?,
    val poster: String?,
    val rating: Double?,
)