package com.example.proyecto_gestion_peliculas.domain

import java.time.LocalDate

data class Film(
    val id : Int,
    val title : String,
    val releaseDate : LocalDate?,
    val genres: List<Genre>,
    val director : String?,
    val actors : List<Actor>,
    val synopsis : String,
    val poster : String?,
    val rating: Double
)
