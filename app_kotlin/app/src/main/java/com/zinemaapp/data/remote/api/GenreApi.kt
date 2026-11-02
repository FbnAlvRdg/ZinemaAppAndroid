package com.zinemaapp.data.remote.api

import com.zinemaapp.domain.model.Genre
import com.zinemaapp.data.remote.dto.credits.GenreDTO
import retrofit2.http.GET

interface GenreApi {
    @GET("genres/films")
    suspend fun getFilmGenres(): List<GenreDTO>

    @GET("genres/tv")
    suspend fun getTvGenres(): List<GenreDTO>
}