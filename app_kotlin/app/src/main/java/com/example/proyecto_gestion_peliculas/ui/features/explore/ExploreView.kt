package com.example.proyecto_gestion_peliculas.ui.features.explore


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.ui.components.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.features.film.toprated.TopRatedFilmsViewModel
import com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated.TopRatedSeriesViewModel
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun ExploreScreen(navigator: Navigator) {
    val viewModel: ExploreViewModel = hiltViewModel()
    val topRatedFilmsViewModel: TopRatedFilmsViewModel = hiltViewModel()
    val topRatedSeriesViewModel: TopRatedSeriesViewModel = hiltViewModel()
    val selectedTab = viewModel.selectedTab
    val films = viewModel.films.collectAsLazyPagingItems()

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
                onList = {},
                onLogOut = {
                    viewModel.logout {
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
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = film.title
                                    )
                                }
                            }

                        }
                    }
                }

                1 -> {
                    LazyColumn {
                        items(topRatedSeriesViewModel.tvSeries) { tvSerie ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = tvSerie.name ?: "No existe titulo"
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}