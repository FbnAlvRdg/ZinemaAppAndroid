package com.example.proyecto_gestion_peliculas.data.remote.paging.bygenre

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.proyecto_gestion_peliculas.data.remote.datasource.tvserie.TvSerieDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie

class SeriesByGenrePagingSource(
    private val dataSource: TvSerieDataSource,
    private val genreId: Int
) : PagingSource<Int, TvSerie>() {
    override fun getRefreshKey(state: PagingState<Int, TvSerie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSerie> {
        return try {
            val page = params.key ?: 1
            val response = dataSource.getSeriesByGenre(genreId, page)

            LoadResult.Page(
                data = response.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}