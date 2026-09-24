package com.example.proyecto_gestion_peliculas.ui.features.explore


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.components.dialogs.AddToListDialog
import com.example.proyecto_gestion_peliculas.ui.features.lists.ListsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.items.ListItemViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun ExploreScreen(navigator: Navigator) {

    val viewModel: ExploreViewModel = hiltViewModel()
    val listsViewModel: ListsViewModel = hiltViewModel()
    val listItemViewModel: ListItemViewModel = hiltViewModel()

    val selectedTab = viewModel.selectedTab
    val films = viewModel.films?.collectAsLazyPagingItems()
    val tvSeries = viewModel.tvSeries?.collectAsLazyPagingItems()

    val lists = listsViewModel.lists

    var showDialog by remember { mutableStateOf(false) }
    var selectedFilmGenre by remember { mutableStateOf<Genre?>(null) }
    var selectedTvSerieGenre by remember { mutableStateOf<Genre?>(null) }
    var selectedTmdbId by remember { mutableStateOf<Long?>(null) }
    var selectedType by remember { mutableStateOf<String?>(null) }
    var selectedTitle by remember { mutableStateOf<String?>(null) }
    var selectedPoster by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val listItemError = listItemViewModel.uiState.error
    val confirmation = listItemViewModel.confirmation
    val uiState = viewModel.uiState
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }


    LaunchedEffect(Unit) {
        listsViewModel.loadLists()
        viewModel.getFilmGenres()
        viewModel.getTvSeriesGenres()
        viewModel.loadInitialFilms()
        viewModel.loadInitialTvSeries()
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

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Explorar",
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
                    }
                )

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
                    LazyRow(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        items(viewModel.filmGenres) { genre ->
                            FilterChip(
                                modifier = Modifier.width(150.dp),
                                selected = selectedFilmGenre == genre,
                                onClick = {
                                    selectedFilmGenre = genre
                                    viewModel.getFilmsByGenre(genre.id)
                                },
                                label = {
                                    Text(
                                        text = genre.name,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        fontSize = 12.sp
                                    )
                                }
                            )
                        }
                    }

                    films?.let { films ->
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(films.itemCount) { index ->
                                val film = films[index]
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = film?.title ?: "No disponible"
                                )
                            }
                        }
                    }
                }

                1 -> {
                    LazyRow(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(viewModel.tvSeriesGenres) { genre ->
                            FilterChip(
                                modifier = Modifier.width(150.dp),
                                selected = selectedTvSerieGenre == genre,
                                onClick = {
                                    selectedTvSerieGenre = genre
                                    viewModel.getSeriesByGenre(genre.id)
                                },
                                label = {
                                    Text(
                                        text = genre.name,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        fontSize = 12.sp
                                    )
                                }
                            )
                        }
                    }

                    tvSeries?.let { tvSeries ->
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(tvSeries.itemCount) { index ->
                                val tvSerie = tvSeries[index]
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = tvSerie?.name ?: "No disponible"
                                )
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

