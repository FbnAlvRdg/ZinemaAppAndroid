package com.zinemaapp.domain.usecase.genre

import com.zinemaapp.data.repository.GenreRepositoryImpl
import com.zinemaapp.domain.model.Genre
import javax.inject.Inject


class GetTvSeriesGenresUseCase @Inject constructor(private val repository: GenreRepositoryImpl) {
    suspend operator fun invoke() : List<Genre> {
        return repository.getTvGenres()
    }
}