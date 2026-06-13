package com.example.proyecto_gestion_peliculas.ui.components.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_gestion_peliculas.domain.model.ListResponse

@Composable
fun AddToListDialog(
    show: Boolean,
    lists: List<ListResponse>,
    onDismiss: () -> Unit,
    onConfirm: (listId: Long) -> Unit
) {
    if (!show) return
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Añadir a lista") },
        text = {
            LazyColumn {
                items(lists) { list ->
                    Text(
                        text = list.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onConfirm(list.id.toLong())
                            }
                            .padding(12.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}


