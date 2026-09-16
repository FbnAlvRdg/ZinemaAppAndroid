package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.credits.GenreDTO
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import retrofit2.http.GET

interface GenreApi {
    @GET("genres/films")
    suspend fun getFilmGenres(): List<GenreDTO>

    @GET("genres/tv")
    suspend fun getTvGenres(): List<GenreDTO>
}