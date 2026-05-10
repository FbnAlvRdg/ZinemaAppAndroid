package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO

interface TvSerieDataSource {
    suspend fun getTopRated(page : Int) : List<TvSerieDTO>
}