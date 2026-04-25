package com.example.proyecto_gestion_peliculas.ui.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.data.remote.repository.FilmRepository
import com.example.proyecto_gestion_peliculas.domain.Film
import kotlinx.coroutines.launch


class EjemploViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    var films by mutableStateOf<List<Film>>(emptyList())
        private set

    fun loadFilms() {
        viewModelScope.launch {
            films = filmRepository.getFilms()
        }
    }
}