package com.zinemaapp.data.repository

import com.zinemaapp.data.remote.datasource.genres.GenreDataSource
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.domain.model.Genre
import com.zinemaapp.domain.repository.GenreRepository
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