package com.example.proyecto_gestion_peliculas.ui.features.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetFilmsByIdUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetSerieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFilmsByIdUseCase: GetFilmsByIdUseCase,
    private val getSerieByIdUseCase: GetSerieByIdUseCase
) :
    ViewModel() {

    var header = "Detalles"
    var film by mutableStateOf<Film?>(null)
        private set
    var serie by mutableStateOf<TvSerie?>(null)
        private set

    fun loadFilm(id: Int) {
        viewModelScope.launch {
            film = getFilmsByIdUseCase(id)
        }
    }

    fun loadSerie(id: Int) {
        viewModelScope.launch {
            serie = getSerieByIdUseCase(id)
        }
    }
}