package com.example.proyecto_gestion_peliculas.ui.components.cards

import android.util.Log
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
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie


@Composable
fun TvSerieCard(tvSerie: TvSerie, onDetail: () -> Unit, onLongClick: () -> Unit) {
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
                model = tvSerie.poster,
                contentDescription = "TvSerie Poster",
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
                    text = tvSerie.name ?: "Sin título",
                    style = MaterialTheme.typography.titleSmall,
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
                            text = "${tvSerie.firstAireDate}",
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
                            text = "${tvSerie.rating ?: "N/A"}",
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                            style = MaterialTheme.typography.labelMedium
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "Estrella",
                            tint = Color.Blue
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    tvSerie.genres.take(2).forEach { genre ->
                        Surface(
                            shape = RoundedCornerShape(50),
                        ) {
                            Text(
                                text = genre?.name.toString(),
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 2.dp
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    tvSerie.originCountry.take(2).forEach { country ->
                        Surface(
                            shape = RoundedCornerShape(50),
                        ) {
                            Text(
                                text = country.toString(),
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 2.dp
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

