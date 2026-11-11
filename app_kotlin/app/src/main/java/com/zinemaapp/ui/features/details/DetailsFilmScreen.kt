package com.zinemaapp.ui.features.details

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.zinemaapp.ui.components.topbar.AppTopBar
import com.zinemaapp.ui.navigation.navigator.Navigator
import com.zinemaapp.core.error.Error
import java.time.format.DateTimeFormatter


@Composable
fun DetailFilmScreen(id: Int, navigator: Navigator) {
    val scroll = rememberScrollState()
    val viewModel: DetailsViewModel = hiltViewModel()
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(id) {
        viewModel.loadFilm(id)
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            val message = when (error) {
                Error.CONNECTION_ERROR ->
                    "No se ha podido conectar con el servidor"

                Error.NOT_FOUND ->
                    "No se ha encontrado la película"

                Error.SERVER_ERROR ->
                    "El servidor no está disponible"

                Error.UNAUTHORIZED ->
                    "Tu sesión ha caducado"

                Error.FORBIDDEN ->
                    "No tienes permisos para acceder"

                Error.VALIDATION_ERROR ->
                    "Los datos no son válidos"

                Error.CONFLICT ->
                    "Se ha producido un conflicto"

                Error.INVALID_CREDENTIALS ->
                    "Las credenciales no son correctas"

                Error.UNKNOWN ->
                    "Ha ocurrido un error inesperado"
            }
            snackbarHostState.showSnackbar(message)
            viewModel.clearError()
        }
    }

    val film = viewModel.film

    Scaffold(
        topBar = {
            AppTopBar(
                title = viewModel.header,
                back = { navigator.back() }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

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
                        model = film?.poster,
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
                    film?.title?.let {
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
                    film?.genres?.joinToString(", ") { genre -> genre.name }?.let {
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
                    film?.actors?.let {
                        Text(
                            text = it.joinToString(", ") { actor -> actor.name },
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Lanzamiento",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = film?.releaseDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            ?: "-",
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Valoración",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "⭐ ${film?.rating ?: "-"}",
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

                film?.synopsis?.let {
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
