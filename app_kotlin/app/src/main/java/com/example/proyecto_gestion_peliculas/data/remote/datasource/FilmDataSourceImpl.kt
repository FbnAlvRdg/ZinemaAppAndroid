package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.FilmDTO

class FilmDataSource ( private val api: FilmApi ) {
    suspend fun getPopularFilms() : List<FilmDTO> {
        return 
    }
}