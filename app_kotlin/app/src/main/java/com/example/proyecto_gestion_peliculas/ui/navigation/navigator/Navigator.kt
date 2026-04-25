package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

import androidx.navigation3.runtime.NavBackStack

interface Navigator {
    fun back()
    fun toLogIn()
    fun toSignUp()

    fun toFilmList()

    fun toEjemplo()
}