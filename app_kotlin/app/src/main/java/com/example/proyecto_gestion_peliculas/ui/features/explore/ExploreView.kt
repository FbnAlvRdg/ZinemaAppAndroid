package com.example.proyecto_gestion_peliculas.ui.features.explore


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto_gestion_peliculas.domain.model.Genre
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.cards.FilmCard
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.components.cards.TvSerieCard
import com.example.proyecto_gestion_peliculas.ui.components.dialogs.AddToListDialog
import com.example.proyecto_gestion_peliculas.ui.features.film.toprated.TopRatedFilmsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.ListsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.lists.items.ListItemViewModel
import com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated.TopRatedSeriesViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun ExploreScreen(navigator: Navigator) {

    val viewModel: ExploreViewModel = hiltViewModel()
    val listsViewModel: ListsViewModel = hiltViewModel()
    val listItemViewModel: ListItemViewModel = hiltViewModel()

    val selectedTab = viewModel.selectedTab
    val films = viewModel.films?.collectAsLazyPagingItems()
    val series = viewModel.series.collectAsLazyPagingItems()

    val lists = listsViewModel.lists

    var showDialog by remember { mutableStateOf(false) }
    var selectedGenre by remember { mutableStateOf<Genre?>(null) }
    var selectedTmdbId by remember { mutableStateOf<Long?>(null) }
    var selectedType by remember { mutableStateOf<String?>(null) }
    var selectedTitle by remember { mutableStateOf<String?>(null) }
    var selectedPoster by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val confirmation = listItemViewModel.confirmation
    val error = listItemViewModel.error


    LaunchedEffect(Unit) {
        listsViewModel.loadLists()
        viewModel.getFilmGenres()
        viewModel.getTvSeriesGenres()
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
                title = "Descubrir",
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
                                selected = selectedGenre == genre,
                                onClick = {
                                    selectedGenre = genre
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

                    Text(
                        text = films?.loadState?.refresh.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }



                1 -> {
                    LazyRow(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(viewModel.tvSeriesGenres) { genre ->
                            FilterChip(
                                modifier = Modifier.width(150.dp),
                                selected = selectedGenre == genre,
                                onClick = { selectedGenre = genre },
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

