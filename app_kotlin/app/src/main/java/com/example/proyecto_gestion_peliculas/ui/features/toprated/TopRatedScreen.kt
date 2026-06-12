package com.example.proyecto_gestion_peliculas.ui.features.toprated

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.cards.FilmCard
import com.example.proyecto_gestion_peliculas.ui.components.cards.TvSerieCard
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.components.topbar.MainTopBar
import com.example.proyecto_gestion_peliculas.ui.features.film.toprated.TopRatedFilmsViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator


@Composable
fun TopRatedScreen(navigator: Navigator) {

    val viewModel: TopRatedViewModel = hiltViewModel()
    val selectedTab = viewModel.selectedTab
    val films = viewModel.films.collectAsLazyPagingItems()
    val series = viewModel.series.collectAsLazyPagingItems()

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
                onList = {},
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
                                    onList = {})
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
                                    onList = {})
                            }
                        }
                    }
                }
            }
        }
    }
}