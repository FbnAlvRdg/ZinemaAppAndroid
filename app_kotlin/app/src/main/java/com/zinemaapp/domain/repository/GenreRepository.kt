package com.zinemaapp.domain.repository

import com.zinemaapp.domain.model.Genre

interface GenreRepository {
    suspend fun getFilmGenres() : List<Genre>
    suspend fun getTvGenres() : List<Genre>
}