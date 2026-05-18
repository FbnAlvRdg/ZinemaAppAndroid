package com.example.proyecto_gestion_peliculas.ui.features.film.bygenre

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsByGenreViewModel @Inject constructor(private val getFilmsByGenreUseCase: GetFilmsByGenreUseCase) :
    ViewModel() {
    var films by mutableStateOf<List<Film>>(emptyList())
    var page = 1
        private set

    val genres = listOf(
        Genre(28, "Action"),

        Genre(18, "Drama"),

        Genre(35, "Comedy"),

        Genre(27, "Horror")

    )

    fun loadFilmsByGenre(idGenre: Int) {
        viewModelScope.launch {
            films = getFilmsByGenreUseCase(idGenre, page)
        }
    }
}