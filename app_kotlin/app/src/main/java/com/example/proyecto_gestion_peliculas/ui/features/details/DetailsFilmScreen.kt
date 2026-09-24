package com.example.proyecto_gestion_peliculas.ui.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun DetailFilmScreen(id: Int, navigator: Navigator) {
    val scroll = rememberScrollState()
    val viewModel: DetailsViewModel = hiltViewModel()

    LaunchedEffect(id) {
        viewModel.loadFilm(id)
    }

    val film = viewModel.film ?: return

    Scaffold(
        topBar = {
            AppTopBar(
                title = viewModel.header,
                back = { navigator.back() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(state = scroll)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    AsyncImage(
                        model = film.poster,
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .aspectRatio(2f / 3f)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = film.title,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Géneros",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = film.genres.joinToString(", ") { genre -> genre.name },
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Actores",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = film.actors.joinToString(", ") { actor -> actor.name },
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Lanzamiento",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = film.releaseDate?.toString() ?: "-",
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Valoración",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "⭐ ${film.rating ?: "-"}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Sinopsis",
                    style = MaterialTheme.typography.titleSmall
                )

                film.synopsis?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify

                    )
                }
            }
        }
    }
}
