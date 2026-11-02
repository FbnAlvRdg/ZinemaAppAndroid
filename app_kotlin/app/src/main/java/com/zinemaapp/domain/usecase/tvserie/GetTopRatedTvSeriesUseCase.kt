package com.zinemaapp.domain.usecase.tvserie

import android.util.Log
import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.repository.TvSerieRepository
import javax.inject.Inject

class GetTopRatedTvSeriesUseCase @Inject constructor(private val repository: TvSerieRepository) {
    suspend operator fun invoke(page: Int): List<TvSerie> {
        return repository.getTopRatedSeries(page)
    }
}