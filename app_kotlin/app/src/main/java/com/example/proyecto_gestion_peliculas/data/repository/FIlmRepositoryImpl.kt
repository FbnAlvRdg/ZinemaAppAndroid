package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.datasource.FilmDataSourceImpl
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject


class FilmRepositoryImpl @Inject constructor(private val dataSourceImpl: FilmDataSourceImpl) : FilmRepository {
    override suspend fun getPopularFilms(): List<Film> {
        return dataSourceImpl.getPopularFilms().map { film ->
            film.toDomain()
        }
    }

    override suspend fun getFilmById(id: Int): Film {
        return dataSourceImpl.getFilmById(id).toDomain()
    }
}