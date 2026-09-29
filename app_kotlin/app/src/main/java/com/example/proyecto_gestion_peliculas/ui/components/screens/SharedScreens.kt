package com.example.proyecto_gestion_peliculas.ui.components.screens

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.ui.model.Media
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MediaScreen(navigator: Navigator, media: Media, onLongClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(
                onClick = {
                    when (media) {
                        is Media.MediaFilm -> {
                            navigator.navigateToDetailsFilm(media.film.id)
                        }

                        is Media.MediaSerie -> {
                            navigator.navigateToDetailsSerie(media.serie.id)
                        }
                    }
                },
                onLongClick = {
                    onLongClick()
                }
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = when (media) {
                    is Media.MediaFilm -> {
                        media.film.poster
                    }

                    is Media.MediaSerie -> {
                        media.serie.poster
                    }
                },
                contentDescription = "Poster",
                modifier = Modifier
                    .width(70.dp)
                    .aspectRatio(2f / 3f)

            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                when (media) {
                    is Media.MediaFilm -> media.film.title
                    is Media.MediaSerie -> media.serie.name
                }?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                when (media) {
                    is Media.MediaFilm -> media.film.rating
                    is Media.MediaSerie -> media.serie.rating
                }?.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                when (media) {
                    is Media.MediaFilm -> media.film.releaseDate?.format(
                        DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy"
                        )
                    )

                    is Media.MediaSerie -> media.serie.firstAireDate?.let {
                        LocalDate.parse(it).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    }
                }.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
