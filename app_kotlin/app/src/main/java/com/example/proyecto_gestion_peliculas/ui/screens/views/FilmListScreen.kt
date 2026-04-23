package com.example.proyecto_gestion_peliculas.ui.screens.views

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.data.listarPeliculasPantalla
import com.example.proyecto_gestion_peliculas.domain.Pelicula

import com.example.proyecto_gestion_peliculas.ui.components.MyBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.MyTopBar


@Composable
fun FilmListScreen(
    back: () -> Unit,
    toAddFilm: () -> Unit,
    toEditScreen: () -> Unit,
    toDetailFilmScreen: (Pelicula) -> Unit
) {
    val header = "Librería de Películas"
    val deleteShowDialog = remember { mutableStateOf(false) }
    val selectedFilm = remember { mutableStateOf<Pelicula?>(null) }
    Scaffold(
        topBar = { MyTopBar(header) },
        bottomBar = { MyBottomBar(back, toAddFilm) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(listarPeliculasPantalla()) { pelicula ->

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
                                selectedFilm.value = pelicula
                                toDetailFilmScreen(pelicula)
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
                        Image(
                            contentDescription = "Portada",
                            painter = painterResource(pelicula.poster),
                            modifier = Modifier
                                .width(120.dp)
                                .aspectRatio(2f / 3f),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(20.dp))

                        Column(modifier = Modifier.weight(0.5f)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = pelicula.title,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Text(
                                text = "Género: " + pelicula.genero
                            )
                            Text(
                                text = "Director: " + pelicula.director
                            )

                            Text(
                                text = "Año: " + pelicula.anio
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Valoración: " + pelicula.valoracion
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Image(
                                    contentDescription = "star",
                                    painter = painterResource(R.drawable.star)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                IconButton(
                                    onClick = { toEditScreen() },
                                ) {
                                    Image(
                                        contentDescription = "Edición",
                                        painter = painterResource(R.drawable.icono_edit),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        deleteShowDialog.value = true
                                    }
                                ) {
                                    Image(
                                        contentDescription = "Borrar",
                                        painter = painterResource(R.drawable.icono_delete),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                                    )
                                }
                            }
                        }
                    }
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

