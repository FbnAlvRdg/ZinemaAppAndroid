package com.example.proyecto_gestion_peliculas.domain.usecase.tvserie

import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import javax.inject.Inject

class GetSerieByIdUseCase @Inject constructor(private val repository: TvSerieRepository) {
    suspend operator fun invoke(id: Int): TvSerie {
        return repository.getSerieById(id)
    }
}