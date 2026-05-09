package com.example.proyecto_gestion_peliculas.domain.usecase

import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(page: Int): List<Film> {
        return repository.getTopRated(page)
    }
}