package com.example.proyecto_gestion_peliculas.ui.navigation.navigator

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.proyecto_gestion_peliculas.ui.navigation.EjemploApiScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.FilmListScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.LoginScreenKey
import com.example.proyecto_gestion_peliculas.ui.navigation.SignUpScreenKey


class NavigatorImpl(private val backStack: NavBackStack<NavKey>) : Navigator {
    override fun back() {
        backStack.removeAt(backStack.lastIndex)
    }

    override fun toLogIn() {
        backStack.add(LoginScreenKey)
    }

    override fun toSignUp() {
        backStack.add(SignUpScreenKey)
    }

    override fun toFilmList() {
        backStack.add(FilmListScreenKey)
    }

    override fun toEjemplo() {
        backStack.add(EjemploApiScreenKey)
    }

}