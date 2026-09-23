package com.example.proyecto_gestion_peliculas.domain.usecase.genre

import com.example.proyecto_gestion_peliculas.data.repository.GenreRepositoryImpl
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import jakarta.inject.Inject

class GetTvSeriesGenresUseCase @Inject constructor(private val repository: GenreRepositoryImpl) {
    suspend operator fun invoke() : List<Genre> {
        return repository.getTvGenres()
    }
}