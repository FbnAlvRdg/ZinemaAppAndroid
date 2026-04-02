package com.example.proyecto_gestion_peliculas.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.ui.theme.BlancoTexto
import com.example.proyecto_gestion_peliculas.ui.theme.RojoCine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(header: String) {
    TopAppBar(
        title = {
            Text(
                text = header,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}