package com.example.proyecto_gestion_peliculas.ui.uistate

import com.example.proyecto_gestion_peliculas.core.error.Error

data class UiState(
    val isLoading : Boolean = false,
    val error : Error? = null
)