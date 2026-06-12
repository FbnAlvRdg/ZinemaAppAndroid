package com.example.proyecto_gestion_peliculas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.proyecto_gestion_peliculas.ui.features.explore.ExploreScreen
import com.example.proyecto_gestion_peliculas.ui.features.film.bygenre.FilmsByGenreScreen
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.NavigatorImpl
import com.example.proyecto_gestion_peliculas.ui.features.views.AddFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.details.DetailFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.details.DetailsSerieScreen
import com.example.proyecto_gestion_peliculas.ui.features.views.EditFilmScreen
import com.example.proyecto_gestion_peliculas.ui.features.mostpopular.MostPopularScreen
import com.example.proyecto_gestion_peliculas.ui.features.toprated.TopRatedScreen
import com.example.proyecto_gestion_peliculas.ui.features.login.LoginScreen
import com.example.proyecto_gestion_peliculas.ui.features.signup.SignUpScreen
import com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated.TopRatedSeriesScreen


@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(LoginScreenKey)
    val navigator: Navigator = NavigatorImpl(backStack)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<LoginScreenKey> {
                LoginScreen(
                    navigator = navigator,
                )
            }

            entry<SignUpScreenKey> {
                SignUpScreen(
                    navigator = navigator
                )
            }

            entry<ExploreScreenKey> {
                ExploreScreen(navigator = navigator)
            }

            entry<MostPopularScreenKey> {
                MostPopularScreen(navigator = navigator)
            }

            entry<FilmsByGenreScreenKey> {
                FilmsByGenreScreen()
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

            entry<DetailFilmScreenKey> { key ->
                DetailFilmScreen(id = key.id, navigator = navigator)
            }

            entry<DetailSerieScreenKey> { key ->
                DetailsSerieScreen(id = key.id, navigator = navigator)
            }

            entry<TopRatedScreenKey> {
                TopRatedScreen(navigator = navigator)
            }

            entry<TopRatedSeriesScreenKey> {
                TopRatedSeriesScreen(navigator = navigator)
            }
        }
    )
}


