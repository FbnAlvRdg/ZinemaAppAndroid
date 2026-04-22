package com.example.proyecto_gestion_peliculas.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownGeneros(generos: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var generoSeleccionado by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = generoSeleccionado,
            onValueChange = {},
            readOnly = true,
            label = {
                Text (
                    text = "Género",
                ) },
            modifier = Modifier
                .menuAnchor(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.outline,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedPlaceholderColor = MaterialTheme.colorScheme.outline
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            generos.forEach { genero ->
                DropdownMenuItem(
                    text = { Text(text = genero) },
                    onClick = {
                        generoSeleccionado = genero
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExposedDropDownActores(actores: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var actorSeleccionado by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = actorSeleccionado,
            onValueChange = {},
            readOnly = true,
            label = {
                Text (
                    text = "Actor/Actriz",
                ) },
            modifier = Modifier
                .menuAnchor(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colorScheme.outline,
                unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedPlaceholderColor = MaterialTheme.colorScheme.outline
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            actores.forEach { actor ->
                DropdownMenuItem(
                    text = { Text(text = actor) },
                    onClick = {
                        actorSeleccionado = actor
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}


