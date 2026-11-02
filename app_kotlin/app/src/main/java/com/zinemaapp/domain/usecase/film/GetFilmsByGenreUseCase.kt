package com.zinemaapp.domain.usecase.film

import androidx.paging.PagingData
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsByGenreUseCase @Inject constructor(private val repository: FilmRepository) {
    operator fun invoke(idGenre: Int): Flow<PagingData<Film>> {
        return repository.getFilmsByGenre(idGenre)
    }
}