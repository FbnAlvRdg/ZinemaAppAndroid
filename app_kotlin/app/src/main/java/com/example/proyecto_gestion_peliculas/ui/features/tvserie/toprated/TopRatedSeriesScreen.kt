package com.example.proyecto_gestion_peliculas.ui.features.tvserie.toprated

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyecto_gestion_peliculas.ui.components.MyTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun TopRatedSeriesScreen(navigator: Navigator) {
    val viewModel: TopRatedSeriesViewModel = hiltViewModel()
    Scaffold(
        topBar = { MyTopBar("Top Rated") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(viewModel.tvSeries) {
                tvSerie ->
                Text(
                    text = tvSerie.name ?: "Sin titulo"
                )
            }
        }
    }
}