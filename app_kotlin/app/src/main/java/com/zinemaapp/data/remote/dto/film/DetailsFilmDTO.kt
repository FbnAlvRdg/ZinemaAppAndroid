package com.zinemaapp.data.remote.dto.film

import com.zinemaapp.data.remote.dto.credits.ActorDTO
import com.zinemaapp.data.remote.dto.credits.GenreDTO


data class DetailsFilmDTO(
    val id: Int,
    val title: String?,
    val originalTitle: String?,
    val releaseDate: String?,
    val synopsis: String?,
    val poster: String?,
    val rating: Double?,
    val actors: List<ActorDTO>,
    val director : String?,
    val genres: List<GenreDTO>?
)