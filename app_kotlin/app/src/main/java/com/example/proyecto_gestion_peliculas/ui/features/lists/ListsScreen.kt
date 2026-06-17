package com.example.proyecto_gestion_peliculas.ui.features.lists


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.ui.components.bottombar.AppBottomBar
import com.example.proyecto_gestion_peliculas.ui.components.dialogs.ConfirmationDialog
import com.example.proyecto_gestion_peliculas.ui.components.topbar.AppTopBar
import com.example.proyecto_gestion_peliculas.ui.navigation.navigator.Navigator

@Composable
fun ListScreen(navigator: Navigator) {
    val viewModel: ListsViewModel = hiltViewModel()
    val lists = viewModel.lists
    val header = viewModel.header
    var showDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedListId by remember { mutableStateOf<Long?>(null) }
    var listName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppTopBar(
                title = header,
                back = { navigator.back() }
            )
        },
        bottomBar = {
            AppBottomBar(
                onHome = { navigator.navigateToExplore() },
                onMostPopular = { navigator.navigateToMostPopularFilms() },
                onTopRated = { navigator.navigateToTopRatedFilms() },
                onList = { navigator.navigateToLists() },
                onLogOut = {
                    viewModel.logOut {
                        navigator.navigateToLoginClearBackStack()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.icono_add),
                    contentDescription = "Add"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(lists) { list ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navigator.navigateToListItems(list.id.toLong())
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = list.name)
                        IconButton(
                            onClick = {
                                selectedListId = list.id.toLong()
                                showDeleteDialog = true
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icono_delete),
                                contentDescription = "Delete List"
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.createList(listName)
                        listName = ""
                        showDialog = false
                    }
                ) {
                    Text("Crear")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { }
                ) {
                    Text("Cancelar")
                }
            },
            title = { Text("Nueva lista") },
            text = {
                TextField(
                    value = listName,
                    onValueChange = { listName = it },
                    placeholder = { Text("Nombre de la lista") }
                )
            }
        )
    }

    ConfirmationDialog(
        show = showDeleteDialog,
        title = "Eliminar lista",
        message = "¿Seguro que quieres eliminar la lista?",
        onConfirm = {
            selectedListId?.let {
                viewModel.deleteList(it)
            }
            showDeleteDialog = false
            selectedListId = null
        },
        onDismiss = {
            showDeleteDialog = false
            selectedListId = null
        }
    )
}

