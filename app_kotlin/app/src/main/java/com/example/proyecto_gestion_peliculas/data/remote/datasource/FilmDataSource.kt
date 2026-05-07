package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.PopularFilmsDTO

interface FilmDataSource {
    suspend fun getPopularFilms(page : Int) : List<PopularFilmsDTO>
    suspend fun getFilmById(id :Int) : DetailsFilmDTO
}