package com.example.proyecto_gestion_peliculas.ui.features.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFilmViewModel @Inject constructor(private val getFilmsByIdUseCase: GetFilmsByIdUseCase) :
    ViewModel() {

    var header = "Detalles"
    var film by mutableStateOf<Film?>(null)
        private set

    fun loadFilm(id: Int) {
        viewModelScope.launch {
            film = getFilmsByIdUseCase(id)
        }
    }
}