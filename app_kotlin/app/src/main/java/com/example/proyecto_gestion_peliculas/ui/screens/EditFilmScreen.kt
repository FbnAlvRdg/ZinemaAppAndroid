package com.example.proyecto_gestion_peliculas.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyecto_gestion_peliculas.data.listarActores
import com.example.proyecto_gestion_peliculas.data.listarGeneros
import com.example.proyecto_gestion_peliculas.ui.components.MyExposedDropDownActores
import com.example.proyecto_gestion_peliculas.ui.components.MyExposedDropDownGeneros
import com.example.proyecto_gestion_peliculas.ui.components.MyTopBar
import kotlinx.coroutines.launch

@Composable
fun EditFilmScreen(back: () -> Unit) {
    var header = "Editar película"
    var titulo by remember { mutableStateOf("") }
    var listaGeneros: List<String> = listarGeneros()
    var director by remember { mutableStateOf("") }
    var listaActores: List<String> = listarActores()
    var anho by remember { mutableStateOf("") }
    var valoracion by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scroll = rememberScrollState()


    Scaffold(
        topBar = { MyTopBar(header) },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = { nuevoTitulo: String -> titulo = nuevoTitulo },
                label = {
                    Text(
                        text = "Titulo"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyExposedDropDownGeneros(listaGeneros)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = director,
                onValueChange = { nuevoDirector: String -> director = nuevoDirector },
                label = {
                    Text(
                        text = "Dirección"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyExposedDropDownActores(listaActores)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = anho,
                onValueChange = { nuevoAnho -> anho = nuevoAnho },
                label = {
                    Text(
                        text = "Año"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = valoracion,
                onValueChange = { nuevaValoracion -> valoracion = nuevaValoracion },
                label = {
                    Text(
                        text = "Valoración"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = MaterialTheme.colorScheme.outline,
                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Seleccionar nueva portada",
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { back() }
                ) {
                    Text(
                        text = "Cancelar"
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Película actualizada")
                            back()
                        }
                    }
                ) {
                    Text(
                        text = "Guardar"
                    )
                }
            }
        }
    }
}