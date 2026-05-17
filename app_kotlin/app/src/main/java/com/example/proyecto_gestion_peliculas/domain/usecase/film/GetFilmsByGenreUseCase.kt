package com.example.proyecto_gestion_peliculas.domain.usecase.film

import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmsByGenre @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(idGenre: Int, page: Int): List<Film> {
        return repository.getFilmsByGenre(idGenre, page)
    }
}