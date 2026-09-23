package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.datasource.genres.GenreDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(private val dataSource: GenreDataSource) :
    GenreRepository {

    override suspend fun getFilmGenres(): List<Genre> {
        return dataSource.getFilmGenres().map { genreDTO ->
            genreDTO.toDomain()
        }
    }

    override suspend fun getTvGenres(): List<Genre> {
        return dataSource.getTvGenres().map { genreDTO ->
            genreDTO.toDomain()
        }
    }
}