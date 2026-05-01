package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.FilmDTO

interface FilmDataSource {
    suspend fun getPopularFilms() : List<FilmDTO>
}