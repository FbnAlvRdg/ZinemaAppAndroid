package com.zinemaapp.ui.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zinemaapp.ui.navigation.DetailFilmScreenKey
import com.zinemaapp.ui.navigation.DetailSerieScreenKey
import com.zinemaapp.ui.navigation.ExploreScreenKey
import com.zinemaapp.ui.navigation.FilmsByGenreScreenKey
import com.zinemaapp.ui.navigation.ListItemsScreenKey
import com.zinemaapp.ui.navigation.ListScreenKey
import com.zinemaapp.ui.navigation.LoginScreenKey
import com.zinemaapp.ui.navigation.MostPopularScreenKey
import com.zinemaapp.ui.navigation.SignUpScreenKey
import com.zinemaapp.ui.navigation.TopRatedScreenKey
import com.zinemaapp.ui.navigation.TopRatedSeriesScreenKey

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

    override fun navigateToLists() {
        backStack.add(ListScreenKey)
    }

    override fun navigateToListItems(listId : Long) {
        backStack.add(ListItemsScreenKey(listId))
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