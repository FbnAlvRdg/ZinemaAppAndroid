package com.example.proyecto_gestion_peliculas.domain.usecase.film

import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedFilmsPagingUseCase @Inject constructor(private val repository: FilmRepository) {
    operator fun invoke(): Flow<PagingData<Film>> {
        return repository.getTopRatedFilmsPaging()
    }
}