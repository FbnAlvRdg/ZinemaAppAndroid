package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

interface Navigator {
    fun back()
    fun navigateToLogIn()
    fun navigateToSignUp()
    fun navigateToExplore()
    fun navigateToMostPopularFilms()
    fun navigateToTopRatedFilms()
    fun navigateToFilmsByGenre()
    fun navigateToTopRatedSeries()
    fun navigateToLoginClearBackStack()
    fun navigateToDetailsFilm(id : Int)
    fun navigateToDetailsSerie(id: Int)
}