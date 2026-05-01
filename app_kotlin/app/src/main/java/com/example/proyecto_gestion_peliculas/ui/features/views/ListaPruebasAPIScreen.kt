package com.example.proyecto_gestion_peliculas.ui.features.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyecto_gestion_peliculas.ui.features.viewmodels.EjemploViewModel

@Composable
fun EjemploScreen() {

    val viewModel: EjemploViewModel = hiltViewModel()
    val films = viewModel.films
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(films) { film ->
            Text(text = film.title)
        }

    }
}