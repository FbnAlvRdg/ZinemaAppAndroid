package com.example.proyecto_gestion_peliculas.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_gestion_peliculas.data.remote.repository.FilmRepository

class EjemploViewModelFactory (private val filmRepository: FilmRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(EjemploViewModel::class.java)) {

            return EjemploViewModel(filmRepository) as T

        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}