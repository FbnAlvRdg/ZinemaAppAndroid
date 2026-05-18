package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.FilmDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {
    @GET("movies/popular")
    suspend fun getPopularFilms(@Query("page") page: Int): List<FilmDTO>

    @GET("movies/top_rated")
    suspend fun getTopRatedFilms(@Query("page") page: Int): List<FilmDTO>

    @GET("movies/{id}")
    suspend fun getFilmById(@Path("id") id: Int): DetailsFilmDTO

    @GET("movies/discover")
    suspend fun getFilmsByGenre(
        @Query("idGenre") idGenre: Int,
        @Query("page") page: Int
    ): List<FilmDTO>
}