package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.domain.model.Film

interface FilmRepository {
    suspend fun getPopularFilms(page: Int): List<Film>
    suspend fun getTopRatedFilms(page: Int): List<Film>
    suspend fun getFilmsByGenre(idGenre: Int, page: Int): List<Film>
    suspend fun getFilmById(id: Int): Film
}