package com.example.proyecto_gestion_peliculas.ui.features.film.mostpopular

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.domain.model.Film

import com.example.proyecto_gestion_peliculas.ui.components.MyBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.MainTopBar
import java.time.format.DateTimeFormatter


@Composable
fun MostPopularScreen(
    back: () -> Unit,
    toAddFilm: () -> Unit,
    toEditScreen: () -> Unit,
    toDetailFilmScreen: (Film) -> Unit
) {
    val viewModel: MostPopularFilmsViewModel = hiltViewModel()
    val lazyListState = rememberLazyListState()
    val header = "Populares"
    val deleteShowDialog = remember { mutableStateOf(false) }
    val selectedFilm = remember { mutableStateOf<Film?>(null) }

    Scaffold(
        topBar = { MainTopBar(header) },
        bottomBar = { MyBottomBar(back, toAddFilm) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            state = lazyListState
        ) {
            items(viewModel.films) { film ->

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                selectedFilm.value = film
                                toDetailFilmScreen(film)
                            },
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 8.dp
                    )

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            contentDescription = "Portada",
                            model = film.poster,
                            modifier = Modifier
                                .width(120.dp)
                                .aspectRatio(2f / 3f),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Column(
                            modifier = Modifier.weight(0.5f),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = film.title,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Titulo Original: ",
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = film.originalTitle
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Fecha: ",
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = film.releaseDate?.format(
                                        DateTimeFormatter.ofPattern(
                                            "dd/MM/yyyy"
                                        )
                                    ) ?: "",
                                    modifier = Modifier.weight(1f)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Valoración: ",
                                    fontWeight = FontWeight.SemiBold
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = film.rating.toString()
                                )
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            snapshotFlow { lazyListState.layoutInfo }
                .collect { layoutInfo ->
                    val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                    val total = layoutInfo.totalItemsCount
                    if (total > 0 && lastVisible >= total - 5) {
                        viewModel.loadFilms()
                    }
                }
        }
    }

    if (deleteShowDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteShowDialog.value = false },
            title = {
                Text(
                    text = "Confirmación"
                )
            },
            text = { Text("¿Estás seguro de que quieres borrar la película?") },
            confirmButton = {
                TextButton(onClick = {
                    deleteShowDialog.value = false
                }) {
                    Text("Seguro")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteShowDialog.value = false }) {
                    Text("No")
                }
            }
        )
    }
}

