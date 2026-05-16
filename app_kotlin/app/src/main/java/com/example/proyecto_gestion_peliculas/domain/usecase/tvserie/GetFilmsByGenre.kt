package com.example.proyecto_gestion_peliculas.domain.usecase.tvserie

import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository

class GetFilmsByGenre(private val repository: FilmRepository) {
    suspend operator fun invoke(idGenre: Int): List<Film> {
        return repository.getFilmsByGenre(idGenre)
    }
}