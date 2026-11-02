package com.zinemaapp.data.remote.datasource.film

import com.zinemaapp.data.remote.dto.film.DetailsFilmDTO
import com.zinemaapp.data.remote.dto.film.FilmDTO

interface FilmDataSource {
    suspend fun getPopularFilms(page: Int): List<FilmDTO>
    suspend fun getTopRated(page: Int): List<FilmDTO>
    suspend fun getFilmById(id: Int): DetailsFilmDTO
    suspend fun getFilmByGenre(idGenre: Int, page: Int): List<FilmDTO>
}