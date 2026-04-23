package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_gestion_peliculas.domain.Pelicula
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.NavigatorImpl
import com.example.proyecto_gestion_peliculas.ui.screens.viewmodels.LoginScreenViewModel
import com.example.proyecto_gestion_peliculas.ui.screens.views.AddFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.views.DetailFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.views.EditFilmScreen
import com.example.proyecto_gestion_peliculas.ui.screens.views.FilmListScreen
import com.example.proyecto_gestion_peliculas.ui.screens.views.LoginScreen
import com.example.proyecto_gestion_peliculas.ui.screens.views.SignUpScreen


@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(LoginScreenKey)
    val navigator: Navigator = NavigatorImpl(backStack)
    val selectedFilm = remember { mutableStateOf<Pelicula?>(null) }


    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<LoginScreenKey> {
               val loginScreenViewModel : LoginScreenViewModel = viewModel()
                LoginScreen(navigator = navigator, loginScreenViewModel = loginScreenViewModel)
            }
            entry<SignUpScreenKey> {
                SignUpScreen() {
                    backStack.remove(backStack.last())
                }
            }
            entry<FilmListScreenKey> {
                FilmListScreen(
                    back = { backStack.remove(backStack.last()) },
                    toAddFilm = { backStack.add(AddFilmScreen) },
                    toEditScreen = { backStack.add(EditFilmScreen) }
                ) { pelicula ->
                    selectedFilm.value = pelicula
                    backStack.add(DetailFilmScreen)
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


