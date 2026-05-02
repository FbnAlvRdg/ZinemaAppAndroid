package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.NavigatorImpl
import com.example.proyecto_gestion_peliculas.ui.features.login.LoginScreenViewModel
import com.example.proyecto_gestion_peliculas.ui.features.views.AddFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.film.details.DetailFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.views.EditFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.views.EjemploScreen
import com.example.proyecto_gestion_peliculas.ui.features.film.mostpopular.MostPopularScreen
import com.example.proyecto_gestion_peliculas.ui.features.login.LoginScreen
import com.example.proyecto_gestion_peliculas.ui.features.signup.SignUpScreen


@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(LoginScreenKey)
    val navigator: Navigator = NavigatorImpl(backStack)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<LoginScreenKey> {
                val loginScreenViewModel: LoginScreenViewModel = viewModel()
                LoginScreen(navigator = navigator, loginScreenViewModel = loginScreenViewModel)
            }
            entry<SignUpScreenKey> {
                SignUpScreen() {
                    backStack.remove(backStack.last())
                }
            }
            entry<FilmListScreenKey> {
                MostPopularScreen(
                    back = { backStack.remove(backStack.last()) },
                    toAddFilm = { backStack.add(AddFilmScreen) },
                    toEditScreen = { backStack.add(EditFilmScreen) }
                ) { film ->
                    backStack.add(DetailFilmScreen(film.id))
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
            entry<DetailFilmScreen> { key ->
                println("ID recibido en navigation: ${key.id}")
                DetailFilmScreen(id = key.id) {
                    backStack.remove(backStack.last())
                }
            }
            entry<EjemploApiScreenKey> {
                EjemploScreen()
            }
        }
    )
}


