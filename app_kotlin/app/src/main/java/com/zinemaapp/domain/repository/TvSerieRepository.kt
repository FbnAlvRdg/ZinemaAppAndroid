package com.zinemaapp.domain.repository

import androidx.paging.PagingData
import com.zinemaapp.domain.model.TvSerie
import kotlinx.coroutines.flow.Flow

interface TvSerieRepository {
    suspend fun getTopRatedSeries(page: Int): List<TvSerie>
    fun getMostPopularSeries(): Flow<PagingData<TvSerie>>
    fun getTopRatedSeriesPaging(): Flow<PagingData<TvSerie>>
    fun getSeriesByGenre(genreId: Int): Flow<PagingData<TvSerie>>
    suspend fun getSerieById(id: Int): TvSerie

}