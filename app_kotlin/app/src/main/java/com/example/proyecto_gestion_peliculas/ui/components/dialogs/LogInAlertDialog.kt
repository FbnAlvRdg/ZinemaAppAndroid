package com.example.proyecto_gestion_peliculas.ui.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LogInAlertDialog(
    show: Boolean,
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(title)
            },
            text = {
                Text(message)
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK")

                }
            }
        )
    }
}