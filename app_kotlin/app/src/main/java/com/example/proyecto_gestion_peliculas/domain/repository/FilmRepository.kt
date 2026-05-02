package com.example.proyecto_gestion_peliculas.domain.repository

import com.example.proyecto_gestion_peliculas.domain.model.Film

interface FilmRepository {
    suspend fun getPopularFilms(): List<Film>
    suspend fun getFilmById(id: Int): Film
}