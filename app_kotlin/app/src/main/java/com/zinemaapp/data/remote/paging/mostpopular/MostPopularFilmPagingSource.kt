package com.zinemaapp.data.remote.paging.mostpopular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zinemaapp.data.remote.datasource.film.FilmDataSource
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.domain.model.Film
import kotlinx.coroutines.CancellationException


class MostPopularFilmPagingSource(private val dataSource: FilmDataSource) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val page = params.key ?: 1
            val response = dataSource.getPopularFilms(page)

            LoadResult.Page(
                data = response.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}