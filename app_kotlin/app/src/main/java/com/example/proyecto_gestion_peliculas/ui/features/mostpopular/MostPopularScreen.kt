package com.example.proyecto_gestion_peliculas.ui.features.mostpopular

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.cards.FilmCard
import com.example.proyecto_gestion_peliculas.ui.components.cards.TvSerieCard
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar

import com.example.proyecto_gestion_peliculas.ui.components.topbar.MainTopBar
import com.example.proyecto_gestion_peliculas.ui.features.explore.ExploreViewModel
import com.example.proyecto_gestion_peliculas.ui.features.film.mostpopular.MostPopularFilmsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.ListsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.items.ListItemViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import java.time.format.DateTimeFormatter


@Composable
fun MostPopularScreen(navigator: Navigator) {

    val viewModel: MostPopularViewModel = hiltViewModel()
    val listsViewModel: ListsViewModel = hiltViewModel()
    val listItemViewModel: ListItemViewModel = hiltViewModel()

    val selectedTab = viewModel.selectedTab
    val films = viewModel.films.collectAsLazyPagingItems()
    val series = viewModel.series.collectAsLazyPagingItems()

    val lists = listsViewModel.lists

    var showDialog by remember { mutableStateOf(false) }
    var selectedTmdbId by remember { mutableStateOf<Long?>(null) }
    var selectedType by remember { mutableStateOf<String?>(null) }
    var selectedTitle by remember { mutableStateOf<String?>(null) }
    var selectedPoster by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val confirmation = listItemViewModel.confirmation
    val error = listItemViewModel.error

    LaunchedEffect(Unit) {
        listsViewModel.loadLists()
    }

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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

                1 -> {
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

        if (showDialog && selectedTmdbId != null && selectedType != null) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    selectedTmdbId = null
                    selectedType = null
                },
                title = { Text("Añadir a lista") },

                text = {
                    LazyColumn {
                        items(lists) { list ->
                            Text(
                                text = list.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        listItemViewModel.addItem(
                                            listId = list.id.toLong(),
                                            tmdbId = selectedTmdbId!!,
                                            type = selectedType!!,
                                            title = "",
                                            poster = ""
                                        )
                                        showDialog = false
                                        selectedTmdbId = null
                                        selectedType = null
                                    }
                                    .padding(12.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        selectedTmdbId = null
                        selectedType = null
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }


//    if (deleteShowDialog.value) {
//        AlertDialog(
//            onDismissRequest = { deleteShowDialog.value = false },
//            title = {
//                Text(
//                    text = "Confirmación"
//                )
//            },
//            text = { Text("¿Estás seguro de que quieres borrar la película?") },
//            confirmButton = {
//                TextButton(onClick = {
//                    deleteShowDialog.value = false
//                }) {
//                    Text("Seguro")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { deleteShowDialog.value = false }) {
//                    Text("No")
//                }
//            }
//        )
//    }
}

