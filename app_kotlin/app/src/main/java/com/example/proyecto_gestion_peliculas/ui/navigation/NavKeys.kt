package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object LoginScreenKey : NavKey

@Serializable
data object SignUpScreenKey : NavKey

@Serializable
data object ExploreScreenKey : NavKey

@Serializable
data object MostPopularScreenKey : NavKey

@Serializable
data object FilmsByGenreScreenKey : NavKey

@Serializable
data object AddFilmScreen : NavKey

@Serializable
data object EditFilmScreen : NavKey

@Serializable
data class DetailFilmScreenKey(val id: Int) : NavKey

@Serializable
data class DetailSerieScreenKey(val id: Int) : NavKey

@Serializable
data object TopRatedScreenKey : NavKey

@Serializable
data object TopRatedSeriesScreenKey : NavKey

