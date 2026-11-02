package com.zinemaapp.domain.usecase.tvserie

import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.repository.TvSerieRepository
import javax.inject.Inject

class GetSerieByIdUseCase @Inject constructor(private val repository: TvSerieRepository) {
    suspend operator fun invoke(id: Int): TvSerie {
        return repository.getSerieById(id)
    }
}