package com.example.proyecto_gestion_peliculas.domain.usecase.tvserie

import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostPopularTvSeriesUseCase @Inject constructor(private val repository: TvSerieRepository) {
    operator fun invoke() : Flow<PagingData<TvSerie>> {
        return repository.getMostPopularSeries()
    }
}