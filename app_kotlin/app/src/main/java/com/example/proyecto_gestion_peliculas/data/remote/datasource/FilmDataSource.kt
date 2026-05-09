package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.FilmsDTO

interface FilmDataSource {
    suspend fun getPopularFilms(page : Int) : List<FilmsDTO>
    suspend fun getTopRated(page: Int) : List<FilmsDTO>
    suspend fun getFilmById(id :Int) : DetailsFilmDTO
}