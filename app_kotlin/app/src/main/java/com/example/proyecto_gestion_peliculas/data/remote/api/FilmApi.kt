package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.PopularFilmsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {
    @GET("movies/popular")
    suspend fun getPopularFilms(@Query("page") page : Int ): List<PopularFilmsDTO>

    @GET("movies/{id}")
    suspend fun getFilmById(@Path("id") id: Int) : DetailsFilmDTO
}