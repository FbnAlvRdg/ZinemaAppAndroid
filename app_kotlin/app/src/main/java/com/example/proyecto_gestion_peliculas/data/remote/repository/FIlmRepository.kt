package com.example.proyecto_gestion_peliculas.data.remote.repository

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.api.RetrofitInstance
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.Film


class FilmRepository(private val api: FilmApi = RetrofitInstance.api) {
    suspend fun getFilms() : List<Film>{
        return api.getFilms().map { film -> film.toDomain()
        }
    }
}