package com.zinemaapp.domain.usecase.tvserie

import androidx.paging.PagingData
import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.repository.TvSerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSeriesByGenreUseCase @Inject constructor(private val repository: TvSerieRepository) {
    operator fun invoke(genreId: Int): Flow<PagingData<TvSerie>> {
        return repository.getSeriesByGenre(genreId)
    }
}