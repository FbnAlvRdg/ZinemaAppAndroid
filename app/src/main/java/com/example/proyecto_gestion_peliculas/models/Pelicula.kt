package com.example.proyecto_gestion_peliculas.models

data class Pelicula(
    var title: String,
    var genero: String,
    var director: String,
    val actores: List<String> = listOf(),
    var anio: Int,
    var valoracion: Double,
    var poster : Int,
    var sinopsis : String
)

