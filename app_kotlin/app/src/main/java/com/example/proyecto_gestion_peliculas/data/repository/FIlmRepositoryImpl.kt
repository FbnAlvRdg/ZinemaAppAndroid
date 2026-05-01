package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject


class FilmRepositoryImpl @Inject constructor(private val api: FilmApi) : FilmRepository {
    override suspend fun getPopularFilms(): List<Film> {
        return api.getPopularFilms().map { film ->
            film.toDomain()
        }
    }
}