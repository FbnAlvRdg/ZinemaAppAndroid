package com.example.proyecto_gestion_peliculas.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.data.remote.datasource.TvSerieDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.data.remote.paging.topRated.TopRatedSeriesPagingSource
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvSerieRepositoryImpl @Inject constructor(private val dataSource: TvSerieDataSource) :
    TvSerieRepository {
    override suspend fun getTopRatedSeries(page: Int): List<TvSerie> {
        return dataSource.getTopRated(page).map { tvSerieDTO ->
            tvSerieDTO.toDomain()
        }
    }

    override fun getTopRatedSeriesPaging(): Flow<PagingData<TvSerie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { TopRatedSeriesPagingSource(dataSource) }
        ).flow
    }

    override suspend fun getSerieById(id: Int): TvSerie {
        return dataSource.getById(id).toDomain()
    }
}