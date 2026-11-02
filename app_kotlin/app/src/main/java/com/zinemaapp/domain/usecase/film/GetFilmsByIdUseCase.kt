package com.zinemaapp.domain.usecase.film

import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmsByIdUseCase @Inject constructor(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(id: Int): Film {
        return filmRepository.getFilmById(id)
    }
}