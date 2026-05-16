package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.FilmDTO
import com.example.proyecto_gestion_peliculas.domain.model.Film

interface FilmDataSource {
    suspend fun getPopularFilms(page: Int): List<FilmDTO>
    suspend fun getTopRated(page: Int): List<FilmDTO>
    suspend fun getFilmById(id: Int): DetailsFilmDTO
    suspend fun getFilmByGenre(idGenre: Int): List<FilmDTO>
}