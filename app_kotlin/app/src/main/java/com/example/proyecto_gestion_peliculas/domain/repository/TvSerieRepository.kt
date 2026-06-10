package com.example.proyecto_gestion_peliculas.domain.repository

import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import kotlinx.coroutines.flow.Flow

interface TvSerieRepository {
    suspend fun getTopRatedSeries(page: Int): List<TvSerie>
    fun getTopRatedSeriesPaging() : Flow<PagingData<TvSerie>>
    suspend fun getSerieById(id: Int): TvSerie


}