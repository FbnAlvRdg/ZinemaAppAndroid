package com.example.proyecto_gestion_peliculas.data.remote.datasource.tvserie

import com.example.proyecto_gestion_peliculas.data.remote.api.TvSerieApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.series.TvSerieDTO
import jakarta.inject.Inject

class TvSerieDataSourceImpl @Inject constructor(private val api: TvSerieApi) : TvSerieDataSource {
    override suspend fun getTopRated(page: Int): List<TvSerieDTO> {
        return api.getTopRatedSeries(page)
    }

    override suspend fun getById(id: Int): TvSerieDTO {
        return api.getSerieById(id)
    }
}