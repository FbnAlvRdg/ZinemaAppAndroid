package com.zinemaapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zinemaapp.data.remote.datasource.tvserie.TvSerieDataSource
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.data.remote.paging.bygenre.SeriesByGenrePagingSource
import com.zinemaapp.data.remote.paging.mostpopular.MostPopularSeriesPagingSource
import com.zinemaapp.data.remote.paging.toprated.TopRatedSeriesPagingSource
import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.repository.TvSerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.collections.map

class TvSerieRepositoryImpl @Inject constructor(private val dataSource: TvSerieDataSource) :
    TvSerieRepository {
    override suspend fun getTopRatedSeries(page: Int): List<TvSerie> {
        return dataSource.getTopRated(page).map { tvSerieDTO ->
            tvSerieDTO.toDomain()
        }
    }

    override fun getMostPopularSeries(): Flow<PagingData<TvSerie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MostPopularSeriesPagingSource(dataSource) }
        ).flow
    }

    override fun getTopRatedSeriesPaging(): Flow<PagingData<TvSerie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { TopRatedSeriesPagingSource(dataSource) }
        ).flow
    }

    override fun getSeriesByGenre(genreId: Int): Flow<PagingData<TvSerie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SeriesByGenrePagingSource(dataSource, genreId) }
        ).flow
    }

    override suspend fun getSerieById(id: Int): TvSerie {
        return dataSource.getById(id).toDomain()
    }
}