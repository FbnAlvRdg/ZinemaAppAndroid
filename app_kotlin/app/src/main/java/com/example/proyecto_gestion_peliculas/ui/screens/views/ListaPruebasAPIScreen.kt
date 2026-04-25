package com.example.proyecto_gestion_peliculas.ui.screens.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.proyecto_gestion_peliculas.ui.screens.viewmodels.EjemploViewModel

@Composable
fun EjemploScreen(viewModel: EjemploViewModel) {
    val films = viewModel.films
    LaunchedEffect(Unit) {
        viewModel.loadFilms()
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(films) { film ->
            Text(text = film.title)
        }

    }
}