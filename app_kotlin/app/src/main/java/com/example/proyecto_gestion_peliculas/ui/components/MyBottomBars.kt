package com.example.proyecto_gestion_peliculas.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyecto_gestion_peliculas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomBar(back: () -> Unit, toAddFilm: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                IconButton(
                    onClick = { back() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                ) {
                    Image(
                        contentDescription = "Back",
                        painter = painterResource(R.drawable.icono_back),
                        modifier = Modifier.size(100.dp),
                    )
                }

            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { toAddFilm()},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Image(
                        contentDescription = "Register",
                        painter = painterResource(R.drawable.icono_add),
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        )
    }
}