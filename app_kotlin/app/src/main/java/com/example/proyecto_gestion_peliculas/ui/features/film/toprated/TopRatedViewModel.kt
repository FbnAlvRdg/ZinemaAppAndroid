package com.example.proyecto_gestion_peliculas.ui.features.film.toprated

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetTopRatedFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val getTopRatedUseCase: GetTopRatedFilmsUseCase) : ViewModel() {
    var films by mutableStateOf<List<Film>>(emptyList())
    var page: Int = 1
        private set

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            val newFilms = getTopRatedUseCase(page)
            films = (films + newFilms).distinctBy { film -> film.id }
            page++
        }
    }
}