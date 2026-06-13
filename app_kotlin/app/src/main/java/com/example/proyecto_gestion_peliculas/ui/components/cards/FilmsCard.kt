package com.example.proyecto_gestion_peliculas.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.domain.model.Film
import java.time.format.DateTimeFormatter

@Composable
fun FilmCard(film: Film, onDetail: () -> Unit, onLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
            .combinedClickable(
                onClick = { onDetail() },
                onLongClick = { onLongClick() }
            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.06f)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = film.poster,
                contentDescription = "Film Poster",
                modifier = Modifier
                    .width(70.dp)
                    .height(100.dp)
            )

            VerticalDivider(
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = film.title,
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = film.originalTitle,
                    style = MaterialTheme.typography.labelMedium
                )

                Surface(
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = film.releaseDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                ?: film.releaseDate.toString(),
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = film.rating.toString(),
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "Star",
                            tint = Color.Blue
                        )
                    }
                }
            }
        }
    }
}