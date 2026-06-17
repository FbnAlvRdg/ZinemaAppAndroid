package com.example.proyecto_gestion_peliculas.data.remote.datasource.tvserie

import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO

interface TvSerieDataSource {
    suspend fun getTopRated(page: Int): List<TvSerieDTO>
    suspend fun getMostPopular(page: Int): List<TvSerieDTO>
    suspend fun getById(id: Int): TvSerieDTO
}