package com.example.proyecto_gestion_peliculas.data.remote.datasource

import com.example.proyecto_gestion_peliculas.data.remote.api.FilmApi
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.DetailsFilmDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.film.PopularFilmsDTO
import javax.inject.Inject

class FilmDataSourceImpl @Inject constructor(private val api: FilmApi) : FilmDataSource {
    override suspend fun getPopularFilms(): List<PopularFilmsDTO> {
        return api.getPopularFilms()
    }

    override suspend fun getFilmById(id: Int): DetailsFilmDTO {
        return api.getFilmById(id)
    }
}