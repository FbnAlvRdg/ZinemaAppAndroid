package com.zinemaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zinemaapp.ui.navigation.Navigation
import com.zinemaapp.ui.theme.Proyecto_gestion_peliculasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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


