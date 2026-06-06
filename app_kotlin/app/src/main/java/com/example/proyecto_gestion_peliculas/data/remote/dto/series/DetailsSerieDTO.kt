package com.example.proyecto_gestion_peliculas.data.remote.dto.series

import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.ActorDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.GenreDTO
import java.time.LocalDate

data class DetailsSerieDTO(
    val id: Int,
    val name: String?,
    val originCountry: List<String?>,
    val firstAireDate : LocalDate,
    val overview: String?,
    val poster: String?,
    val rating: Double?,
    val actors: List<ActorDTO>,
    val genres: List<GenreDTO>
)
