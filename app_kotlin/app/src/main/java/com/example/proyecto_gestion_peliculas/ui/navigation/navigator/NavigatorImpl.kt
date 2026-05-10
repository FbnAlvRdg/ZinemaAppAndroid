package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated.TopRatedSeriesScreen
import com.example.proyecto_gestion_peliculas.ui.navigation.FilmListScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.LoginScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.SignUpScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.TopRatedFilmScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.TopRatedSeriesScreenKey


class NavigatorImpl(private val backStack: NavBackStack<NavKey>) : Navigator {
    override fun back() {
        backStack.removeAt(backStack.lastIndex)
    }

    override fun navigateToLogIn() {
        backStack.add(LoginScreenKey)
    }

    override fun navigateToSignUp() {
        backStack.add(SignUpScreenKey)
    }

    override fun navigateToMostPopularFilms() {
        backStack.add(FilmListScreenKey)
    }

    override fun navigateToTopRatedFilms() {
        backStack.add(TopRatedFilmScreenKey)
    }

    override fun navigateToTopRatedSeries() {
        backStack.add(TopRatedSeriesScreenKey)
    }
}