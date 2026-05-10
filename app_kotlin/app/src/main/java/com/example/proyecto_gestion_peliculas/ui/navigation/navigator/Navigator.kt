package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

interface Navigator {
    fun back()
    fun navigateToLogIn()
    fun navigateToSignUp()
    fun navigateToMostPopularFilms()
    fun navigateToTopRatedFilms()
    fun navigateToTopRatedSeries()
}