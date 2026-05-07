package com.example.proyecto_gestion_peliculas.domain.usecase

import com.example.proyecto_gestion_peliculas.data.repository.FilmRepositoryImpl
import com.example.proyecto_gestion_peliculas.domain.model.Film
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(private val filmRepository: FilmRepositoryImpl) {
    suspend fun invoke(page : Int) : List<Film>{
        return filmRepository.getPopularFilms(page)
    }
}
