package com.example.proyecto_gestion_peliculas.domain.usecase.film

import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject

class GetDetailsFilmUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(id: Int): Film {
        return filmRepository.getFilmById(id)
    }
}