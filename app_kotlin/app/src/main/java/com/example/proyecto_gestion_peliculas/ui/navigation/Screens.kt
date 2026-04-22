package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
data object LoginScreen : NavKey

@Serializable
data object SignUpScreen : NavKey

@Serializable
data object FilmListScreen : NavKey

@Serializable
data object AddFilmScreen : NavKey

@Serializable
data object EditFilmScreen : NavKey

@Serializable
data object DetailFilmScreen : NavKey