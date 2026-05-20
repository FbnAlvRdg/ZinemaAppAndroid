package com.example.proyecto_gestion_peliculas.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.proyecto_gestion_peliculas.data.remote.datasource.FilmDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Film

class TopRatedFilmPagingSource(private val dataSource: FilmDataSource) : PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 1
        val response = dataSource.getTopRated(page)

        return LoadResult.Page(
            data = response.map { it.toDomain() },
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (response.isEmpty()) null else page + 1
        )
    }
}

