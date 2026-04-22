package com.example.proyecto_gestion_peliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyecto_gestion_peliculas.ui.navigation.Navigation
import com.example.proyecto_gestion_peliculas.ui.theme.Proyecto_gestion_peliculasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_gestion_peliculasTheme() {
                Navigation()
            }
        }
    }
}


