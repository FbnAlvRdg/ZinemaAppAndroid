package com.zinemaapp.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.zinemaapp.core.session.SessionManager
import com.zinemaapp.ui.features.details.DetailFilmScreen
import com.zinemaapp.ui.features.details.DetailsSerieScreen
import com.zinemaapp.ui.features.explore.ExploreScreen
import com.zinemaapp.ui.features.lists.ListScreen
import com.zinemaapp.ui.features.lists.items.ListItemsScreen
import com.zinemaapp.ui.features.login.LoginScreen
import com.zinemaapp.ui.features.mostpopular.MostPopularScreen
import com.zinemaapp.ui.features.signup.SignUpScreen
import com.zinemaapp.ui.features.toprated.TopRatedScreen
import com.zinemaapp.ui.navigation.navigator.Navigator
import com.zinemaapp.ui.navigation.navigator.NavigatorImpl


@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(LoginScreenKey)
    val navigator: Navigator = NavigatorImpl(backStack)
    val context = LocalContext.current
    val sessionExpired by SessionManager.sessionExpired.collectAsState()

    LaunchedEffect(sessionExpired) {
        if (sessionExpired) {
            navigator.navigateToLoginClearBackStack()

            Toast.makeText(context, "La sesión ha caducado", Toast.LENGTH_SHORT).show()

            SessionManager.consumeSessionExpired()
        }
    }

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

            entry<ListScreenKey> {
                ListScreen(navigator = navigator)
            }

            entry<ListItemsScreenKey> { key ->
                ListItemsScreen(navigator, key.listId)
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
        }
    )
}


