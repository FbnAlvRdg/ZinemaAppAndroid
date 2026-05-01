package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.api.RetrofitInstance
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Film


class FilmRepository(private val api: FilmApi = RetrofitInstance.api) {
    suspend fun getPopularFilms() : List<Film>{
        return api.getPopularFilms().map { film -> film.toDomain()
        }
    }
}