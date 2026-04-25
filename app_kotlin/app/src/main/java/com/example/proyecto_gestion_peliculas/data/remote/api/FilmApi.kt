package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.FilmDTO
import retrofit2.http.GET

interface FilmApi {
    @GET("movies/popular")
    suspend fun getFilms() : List<FilmDTO>
}