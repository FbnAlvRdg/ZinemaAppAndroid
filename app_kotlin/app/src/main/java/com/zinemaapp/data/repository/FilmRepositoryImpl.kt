package com.zinemaapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zinemaapp.data.remote.datasource.film.FilmDataSource
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.data.remote.paging.bygenre.FilmsByGenrePagingSource
import com.zinemaapp.data.remote.paging.mostpopular.MostPopularFilmPagingSource
import com.zinemaapp.data.remote.paging.toprated.TopRatedFilmPagingSource
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val dataSource: FilmDataSource) :
    FilmRepository {

    override suspend fun getMostPopularFilms(page: Int): List<Film> {
        return dataSource.getPopularFilms(page)
            .map { filmDTO ->
                filmDTO.toDomain()
            }
    }

    override fun getMostPopularFilmsPaging(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MostPopularFilmPagingSource(dataSource) }
        ).flow
    }

    override suspend fun getTopRatedFilms(page: Int): List<Film> {
        return dataSource.getTopRated(page)
            .map { filmDTO ->
                filmDTO.toDomain()
            }
    }

    override fun getTopRatedFilmsPaging(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { TopRatedFilmPagingSource(dataSource) }
        ).flow
    }

    override fun getFilmsByGenre(idGenre: Int): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { FilmsByGenrePagingSource(dataSource, idGenre) }
        ).flow
    }

    override suspend fun getFilmById(id: Int): Film {
        return dataSource.getFilmById(id).toDomain()
    }
}