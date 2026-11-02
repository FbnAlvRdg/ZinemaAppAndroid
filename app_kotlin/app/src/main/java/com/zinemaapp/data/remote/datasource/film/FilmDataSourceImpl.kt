package com.zinemaapp.data.remote.datasource.film

import com.zinemaapp.data.remote.api.FilmApi
import com.zinemaapp.data.remote.dto.film.DetailsFilmDTO
import com.zinemaapp.data.remote.dto.film.FilmDTO
import javax.inject.Inject

class FilmDataSourceImpl @Inject constructor(private val api: FilmApi) : FilmDataSource {

    override suspend fun getPopularFilms(page: Int): List<FilmDTO> {
        return api.getPopularFilms(page)
    }

    override suspend fun getTopRated(page: Int): List<FilmDTO> {
        return api.getTopRatedFilms(page)
    }

    override suspend fun getFilmById(id: Int): DetailsFilmDTO {
        return api.getFilmById(id)
    }

    override suspend fun getFilmByGenre(idGenre: Int, page: Int): List<FilmDTO> {
        return api.getFilmsByGenre(idGenre, page)
    }
}