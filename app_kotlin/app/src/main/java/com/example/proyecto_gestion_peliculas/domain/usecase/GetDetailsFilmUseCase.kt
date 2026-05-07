package com.example.proyecto_gestion_peliculas.domain.usecase

import com.example.proyecto_gestion_peliculas.data.repository.FilmRepositoryImpl
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import javax.inject.Inject

class GetDetailsFilmUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    suspend fun invoke(id: Int): Film {
        return filmRepository.getFilmById(id)
    }
}