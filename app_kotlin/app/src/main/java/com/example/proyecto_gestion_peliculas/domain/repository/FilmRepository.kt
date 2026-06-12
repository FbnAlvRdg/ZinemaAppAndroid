package com.example.proyecto_gestion_peliculas.domain.repository

import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun getMostPopularFilms(page: Int): List<Film>
    fun getMostPopularFilmsPaging() : Flow<PagingData<Film>>
    suspend fun getTopRatedFilms(page: Int): List<Film>
    fun getTopRatedFilmsPaging(): Flow<PagingData<Film>>
    suspend fun getFilmsByGenre(idGenre: Int, page: Int): List<Film>
    suspend fun getFilmById(id: Int): Film
}