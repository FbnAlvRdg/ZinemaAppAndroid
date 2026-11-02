package com.zinemaapp.data.remote.datasource.genres

import com.zinemaapp.data.remote.api.GenreApi
import com.zinemaapp.data.remote.dto.credits.GenreDTO
import javax.inject.Inject

class GenreDataSourceImpl @Inject constructor(private val api: GenreApi) : GenreDataSource {
    override suspend fun getFilmGenres(): List<GenreDTO> {
        return api.getFilmGenres()
    }

    override suspend fun getTvGenres(): List<GenreDTO> {
        return api.getTvGenres()
    }
}