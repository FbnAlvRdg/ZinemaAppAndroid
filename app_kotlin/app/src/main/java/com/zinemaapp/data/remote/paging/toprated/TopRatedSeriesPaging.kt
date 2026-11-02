package com.zinemaapp.data.remote.paging.toprated

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zinemaapp.data.remote.datasource.tvserie.TvSerieDataSource
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.domain.model.TvSerie
import kotlin.collections.map


class TopRatedSeriesPagingSource(private val dataSource: TvSerieDataSource) :
    PagingSource<Int, TvSerie>() {
    override fun getRefreshKey(state: PagingState<Int, TvSerie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSerie> {

        return try {
            val page = params.key ?: 1
            val response = dataSource.getTopRated(page)
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