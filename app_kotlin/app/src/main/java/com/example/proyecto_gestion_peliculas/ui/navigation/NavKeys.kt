package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object LoginScreenKey : NavKey

@Serializable
data object SignUpScreenKey : NavKey

@Serializable
data object FilmListScreenKey : NavKey

@Serializable
data object AddFilmScreen : NavKey

@Serializable
data object EditFilmScreen : NavKey

@Serializable
data object DetailFilmScreen : NavKey

@Serializable
data object EjemploApiScreenKey : NavKey