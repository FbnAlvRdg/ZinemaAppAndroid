package com.zinemaapp.domain.usecase.film

import androidx.paging.PagingData
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedFilmsUseCase @Inject constructor(private val repository: FilmRepository) {
    operator fun invoke(): Flow<PagingData<Film>> {
        return repository.getTopRatedFilmsPaging()
    }
}