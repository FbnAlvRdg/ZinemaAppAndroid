package com.example.proyecto_gestion_peliculas.domain.usecase.film

import com.example.proyecto_gestion_peliculas.data.repository.FilmRepositoryImpl
import com.example.proyecto_gestion_peliculas.domain.model.Film
import javax.inject.Inject

class GetMostPopularFilmsUseCase @Inject constructor(private val filmRepository: FilmRepositoryImpl) {
    suspend fun invoke(page : Int) : List<Film>{
        return filmRepository.getMostPopularFilms(page)
    }
}