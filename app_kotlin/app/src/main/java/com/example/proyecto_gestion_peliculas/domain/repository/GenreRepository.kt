package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.domain.model.Genre

interface GenreRepository {
    suspend fun getFilmGenres() : List<Genre>
    suspend fun getTvGenres() : List<Genre>
}