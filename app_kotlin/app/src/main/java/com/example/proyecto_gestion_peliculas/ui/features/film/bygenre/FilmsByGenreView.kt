package com.example.proyecto_gestion_peliculas.ui.features.film.bygenre

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FilmsByGenreScreen() {
    val viewModel: FilmsByGenreViewModel = hiltViewModel()

    Scaffold(
        topBar = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyRow {
                items(viewModel.genres) { genre ->
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.loadFilmsByGenre(genre.id) },
                        label = {
                            Text(
                                text = genre.name
                            )
                        }
                    )
                }
            }

            LazyColumn {
                items(viewModel.films) { film ->
                    Text(
                        text = film.title
                    )
                }
            }
        }
    }
}