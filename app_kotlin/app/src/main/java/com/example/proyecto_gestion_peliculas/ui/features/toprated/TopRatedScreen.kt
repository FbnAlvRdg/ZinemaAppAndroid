package com.example.proyecto_gestion_peliculas.ui.features.toprated

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.cards.FilmCard
import com.example.proyecto_gestion_peliculas.ui.components.cards.TvSerieCard
import com.example.proyecto_gestion_peliculas.ui.components.dialogs.AddToListDialog
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.features.lists.ListsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.items.ListItemViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator


@Composable
fun TopRatedScreen(navigator: Navigator) {

    val viewModel: TopRatedViewModel = hiltViewModel()
    val listsViewModel: ListsViewModel = hiltViewModel()
    val listItemViewModel: ListItemViewModel = hiltViewModel()

    val selectedTab = viewModel.selectedTab
    val films = viewModel.films?.collectAsLazyPagingItems()
    val series = viewModel.series?.collectAsLazyPagingItems()

    val lists = listsViewModel.lists

    var showDialog by remember { mutableStateOf(false) }
    var selectedTmdbId by remember { mutableStateOf<Long?>(null) }
    var selectedType by remember { mutableStateOf<String?>(null) }
    var selectedTitle by remember { mutableStateOf<String?>(null) }
    var selectedPoster by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val confirmation = listItemViewModel.confirmation
    val listItemError = listItemViewModel.uiState.error
    val uiState = viewModel.uiState
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }


    LaunchedEffect(Unit) {
        listsViewModel.loadLists()
    }

    LaunchedEffect(selectedTab) {
        if (selectedTab == 0) {
            viewModel.getTopRatedFilms()
        } else {
            viewModel.getTopRatedSeries()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            val message = when (error) {
                Error.CONNECTION_ERROR ->
                    "Se ha producido un error de conexión"

                Error.SERVER_ERROR ->
                    "Error en el servidor"

                Error.NOT_FOUND ->
                    "No se ha encontrado el contenido"

                Error.UNKNOWN ->
                    "Ha ocurrido un error inesperado"

                else ->
                    "Error desconocido"
            }

            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )

            viewModel.cleanError()
        }
    }

    LaunchedEffect(listItemError) {
        listItemError?.let {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            listItemViewModel.clearError()
        }
    }

    LaunchedEffect(confirmation) {
        confirmation?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            listItemViewModel.clearConfirmation()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = viewModel.header,
                back = { navigator.back() }
            )
        },

        bottomBar = {
            AppBottomBar(
                onHome = { navigator.navigateToExplore() },
                onMostPopular = { navigator.navigateToMostPopularFilms() },
                onTopRated = { navigator.navigateToTopRatedFilms() },
                onList = { navigator.navigateToLists() },
                onLogOut = {
                    viewModel.logOut {
                        navigator.navigateToLoginClearBackStack()
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PrimaryTabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = viewModel.selectedTab == 0,
                    onClick = { viewModel.selectTab(0) },
                    text = {
                        Text(text = "Peliculas")
                    })
                Tab(
                    selected = viewModel.selectedTab == 1,
                    onClick = { viewModel.selectTab(1) },
                    text = {
                        Text(
                            text = "Series"
                        )
                    }
                )
            }

            when (selectedTab) {
                0 -> {
                    films?.let {
                        LazyColumn {
                            items(films.itemCount) { index ->
                                val film = films[index]
                                film?.let {
                                    FilmCard(
                                        film,
                                        onDetail = { navigator.navigateToDetailsFilm(film.id) },
                                        onLongClick = {
                                            selectedTmdbId = film.id.toLong()
                                            selectedType = "movie"
                                            selectedTitle = film.title
                                            selectedPoster = film.poster
                                            showDialog = true
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                1 -> {
                    series?.let {
                        LazyColumn {
                            items(series.itemCount) { index ->
                                val tvSerie = series[index]
                                tvSerie?.let {
                                    TvSerieCard(
                                        tvSerie,
                                        onDetail = { navigator.navigateToDetailsSerie(tvSerie.id) },
                                        onLongClick = {
                                            selectedTmdbId = tvSerie.id.toLong()
                                            selectedType = "tv"
                                            selectedTitle = tvSerie.name
                                            selectedPoster = tvSerie.poster
                                            showDialog = true
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    AddToListDialog(
        show = showDialog,
        lists = lists,
        onDismiss = {
            showDialog = false
            selectedTmdbId = null
            selectedType = null
        },
        onConfirm = { listId ->
            val tmdbId = selectedTmdbId
            val type = selectedType
            val title = selectedTitle
            val poster = selectedPoster

            if (tmdbId != null && type != null && title != null) {
                listItemViewModel.addItem(
                    listId = listId,
                    tmdbId = tmdbId,
                    type = type,
                    title = title,
                    poster = poster
                )
            }
            showDialog = false
            selectedTmdbId = null
            selectedType = null
        }
    )
}