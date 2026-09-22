package com.example.proyecto_gestion_peliculas.domain.usecase.tvserie

import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class GetSeriesByGenreUseCase @Inject constructor(private val repository: TvSerieRepository) {
    operator fun invoke(genreId: Int): Flow<PagingData<TvSerie>> {
        return repository.getSeriesByGenre(genreId)
    }
}