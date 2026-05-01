package com.example.proyecto_gestion_peliculas.ui.features.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.usecase.GetPopularFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EjemploViewModel @Inject constructor(private val getPopularFilmsUseCase: GetPopularFilmsUseCase) :
    ViewModel() {
    var films by mutableStateOf<List<Film>>(emptyList())
        private set

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            films = getPopularFilmsUseCase.invoke()
        }
    }
}