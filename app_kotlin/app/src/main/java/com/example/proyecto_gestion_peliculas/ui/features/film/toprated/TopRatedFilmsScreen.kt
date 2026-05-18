package com.example.proyecto_gestion_peliculas.ui.features.film.toprated

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyecto_gestion_peliculas.ui.components.MainTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator


@Composable
fun TopRatedScreen(navigator: Navigator) {
    val viewModel: TopRatedFilmsViewModel = hiltViewModel()
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = { MainTopBar("Top Rated") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = lazyListState
        ) {
            items(viewModel.films) { film ->
                Text(
                    text = film.title
                )
            }
        }

    }

    LaunchedEffect(Unit) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val total = layoutInfo.totalItemsCount
                if (total > 0 && lastVisible >= total - 5) { //cuando esta a cinco del final empieza a cargar nuevas peliculas
                    viewModel.loadFilms()
                }
            }
    }
}