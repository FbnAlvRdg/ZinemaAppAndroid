package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.api.TvSerieApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO
import jakarta.inject.Inject

class TvSerieDataSourceImpl @Inject constructor(private val api: TvSerieApi) : TvSerieDataSource {
    override suspend fun getTopRated(page: Int): List<TvSerieDTO> {
        return api.getTopRatedSeries(page)
    }
}