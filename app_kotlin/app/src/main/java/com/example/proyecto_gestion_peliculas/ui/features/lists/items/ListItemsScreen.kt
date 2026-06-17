package com.example.proyecto_gestion_peliculas.ui.features.lists.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.ui.components.dialogs.ConfirmationDialog
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun ListItemsScreen(navigator: Navigator, listId: Long) {
    val viewModel: ListItemViewModel = hiltViewModel()
    val items = viewModel.items
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedItemId by remember { mutableStateOf<Long?>(null) }
    var selectedListId by remember { mutableStateOf<Long?>(null) }


    LaunchedEffect(listId) {
        viewModel.loadItems(listId)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = viewModel.header,
                back = { navigator.back() }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(items) { item ->
                val imageUrl = item.poster?.let {
                    "https://image.tmdb.org/t/p/w500$it"
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            if (item.type == "movie") {
                                navigator.navigateToDetailsFilm(item.tmdbId.toInt())
                            } else {
                                navigator.navigateToDetailsSerie(item.tmdbId.toInt())
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Poster",
                            modifier = Modifier
                                .width(60.dp)
                                .height(90.dp)
                        )
                        Text(text = "${item.title}")
                        IconButton(
                            onClick = {
                                selectedListId = listId
                                selectedItemId = item.id
                                showDeleteDialog = true
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icono_delete),
                                contentDescription = "Delete item"
                            )
                        }
                    }
                }
            }
        }
    }

    ConfirmationDialog(
        show = showDeleteDialog,
        title = "Eliminar elemento",
        message = "¿Seguro que quieres borrar el elememto?",
        onConfirm = {
            selectedListId?.let { listId ->
                selectedItemId?.let { itemId ->
                    viewModel.deleteItem(
                        listId = listId,
                        itemId = itemId
                    )
                }
            }
            showDeleteDialog = false
            selectedListId = null
            selectedItemId = null
        },
        onDismiss = {
            showDeleteDialog = false
            selectedListId = null
            selectedItemId = null
        }
    )
}


