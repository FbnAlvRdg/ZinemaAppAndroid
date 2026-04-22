package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_gestion_peliculas.domain.Pelicula
import com.example.proyecto_gestion_peliculas.ui.screens.AddFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.DetailFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.EditFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.FilmListScreen
import com.example.proyecto_gestion_peliculas.ui.screens.LoginScreen
import com.example.proyecto_gestion_peliculas.ui.screens.SignUpScreen


@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(LoginScreen)
    val selectedFilm = remember { mutableStateOf<Pelicula?>(null) }


    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<LoginScreen> {
                LoginScreen(toSignUp = { backStack.add(SignUpScreen) }) {
                    backStack.add(FilmListScreen)
                }
            }
            entry<SignUpScreen> {
                SignUpScreen(){
                    backStack.remove(backStack.last())
                }
            }
            entry<FilmListScreen> {
                FilmListScreen(
                    back = { backStack.remove(backStack.last()) },
                    toAddFilm = { backStack.add(AddFilmScreen) },
                    toEditScreen = { backStack.add(EditFilmScreen) }
                ) {
                    pelicula -> selectedFilm.value = pelicula
                    backStack.add (DetailFilmScreen)
                }
            }
            entry<AddFilmScreen> {
                AddFilmScreen(
                    back = { backStack.remove(backStack.last()) }
                )
            }
            entry<EditFilmScreen> {
                EditFilmScreen(
                    back = { backStack.remove(backStack.last()) }
                )
            }
            entry<DetailFilmScreen> {
                DetailFilmScreen(pelicula = selectedFilm.value) {
                    backStack.remove(backStack.last())
                }
            }
        }
    )
}


