package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.proyecto_gestion_peliculas.ui.navigation.DetailFilmScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.DetailSerieScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.ExploreScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.MostPopularScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.FilmsByGenreScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.LoginScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.SignUpScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.TopRatedScreenKey
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

    override fun navigateToExplore() {
        backStack.add(ExploreScreenKey)
    }

    override fun navigateToMostPopularFilms() {
        backStack.add(MostPopularScreenKey)
    }

    override fun navigateToTopRatedFilms() {
        backStack.add(TopRatedScreenKey)
    }

    override fun navigateToFilmsByGenre() {
        backStack.add(FilmsByGenreScreenKey)
    }

    override fun navigateToTopRatedSeries() {
        backStack.add(TopRatedSeriesScreenKey)
    }

    override fun navigateToLoginClearBackStack() {
        backStack.clear()
        backStack.add(LoginScreenKey)
    }

    override fun navigateToDetailsFilm(id : Int) {
        backStack.add(DetailFilmScreenKey(id))
    }

    override fun navigateToDetailsSerie(id: Int) {
        backStack.add(DetailSerieScreenKey(id))
    }
}