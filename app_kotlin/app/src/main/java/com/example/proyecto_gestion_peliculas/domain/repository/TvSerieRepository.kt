package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.domain.model.TvSerie

interface TvSerieRepository {
    suspend fun getTopRatedSeries(page: Int): List<TvSerie>
    suspend fun getSerieById(id: Int): TvSerie


}