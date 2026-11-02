package com.zinemaapp.data.remote.dto.series

import com.zinemaapp.data.remote.dto.credits.ActorDTO
import com.zinemaapp.data.remote.dto.credits.GenreDTO


data class TvSerieDTO(
    val id: Int,
    val name: String?,
    val originCountry: List<String?>,
    val overview: String?,
    val poster: String?,
    val rating: Double?,
    val firstAireDate: String?,
    val genres: List<GenreDTO?>,
    val actors: List<ActorDTO?>
)
