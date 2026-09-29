package com.example.proyecto_gestion_peliculas.ui.features.details

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DetailsSerieScreen(id: Int, navigator: Navigator) {
    val scroll = rememberScrollState()
    val viewModel: DetailsViewModel = hiltViewModel()
    val serie = viewModel.serie

    LaunchedEffect(id) {
        viewModel.loadSerie(id)
    }

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
                        model = serie?.poster ?: "-",
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
                    serie?.name?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Géneros",
                        style = MaterialTheme.typography.labelLarge
                    )
                    serie?.genres?.joinToString(", ") { genre -> genre?.name.toString() }?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Actores",
                        style = MaterialTheme.typography.labelLarge
                    )
                    serie?.actors?.joinToString(", ") { actor -> actor?.name.toString() }?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Lanzamiento",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = serie?.firstAireDate ?: "-",
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Valoración",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "⭐ ${serie?.rating ?: "-"}",
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

                serie?.overview?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                } ?: Text(
                    text = "No disponible",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}




