package com.zinemaapp.data.remote.datasource.genres

import com.zinemaapp.data.remote.dto.credits.GenreDTO

interface GenreDataSource {
    suspend fun getFilmGenres(): List<GenreDTO>
    suspend fun getTvGenres(): List<GenreDTO>
}