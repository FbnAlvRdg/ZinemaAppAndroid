package com.example.proyecto_gestion_peliculas.data.remote.api

import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSerieApi {
    @GET("tv/top-rated")
    suspend fun getTopRatedSeries(@Query("page") page: Int): List<TvSerieDTO>

    @GET("tv/most-popular")
    suspend fun getMostPopular(@Query("page") page: Int): List<TvSerieDTO>

    @GET("tv/{id}")
    suspend fun getSerieById(@Path("id") id: Int): TvSerieDTO
}