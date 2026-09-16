package com.example.proyecto_gestion_peliculas.data.remote.datasource.genres

import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.GenreDTO

interface GenreDataSource {
    suspend fun getFilmGenres() : List<GenreDTO>
    suspend fun getTvGenres() : List<GenreDTO>
}