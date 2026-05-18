package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.datasource.FilmDataSource
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject


class FilmRepositoryImpl @Inject constructor(private val dataSource: FilmDataSource) :
    FilmRepository {
    override suspend fun getPopularFilms(page: Int): List<Film> {
        return dataSource.getPopularFilms(page)
            .map { filmDTO ->
                filmDTO.toDomain()
            }
    }

    override suspend fun getTopRatedFilms(page: Int): List<Film> {
        return dataSource.getTopRated(page)
            .map { filmDTO ->
                filmDTO.toDomain()
            }
    }

    override suspend fun getFilmsByGenre(idGenre: Int, page: Int): List<Film> {
        return dataSource.getFilmByGenre(idGenre, page)
            .map { filmDTO -> filmDTO.toDomain() }
    }

    override suspend fun getFilmById(id: Int): Film {
        return dataSource.getFilmById(id).toDomain()
    }
}