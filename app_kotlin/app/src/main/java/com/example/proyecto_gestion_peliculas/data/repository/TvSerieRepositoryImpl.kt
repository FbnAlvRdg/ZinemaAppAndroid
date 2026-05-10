package com.example.proyecto_gestion_peliculas.data.repository

import android.util.Log
import com.example.proyecto_gestion_peliculas.data.remote.datasource.TvSerieDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import javax.inject.Inject

class TvSerieRepositoryImpl @Inject constructor(private val dataSource: TvSerieDataSource) :
    TvSerieRepository {
    override suspend fun getTopRatedSeries(page: Int): List<TvSerie> {
        return dataSource.getTopRated(page).map { tvSerieDTO ->
            tvSerieDTO.toDomain()
        }
    }

}