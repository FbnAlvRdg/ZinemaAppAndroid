package com.example.proyecto_gestion_peliculas.domain.usecase.tvserie

import android.util.Log
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.repository.TvSerieRepository
import javax.inject.Inject

class GetTopRatedTvSeriesUseCase @Inject constructor(private val repository: TvSerieRepository) {
    suspend operator fun invoke(page: Int): List<TvSerie> {
        return repository.getTopRatedSeries(page)
    }
}