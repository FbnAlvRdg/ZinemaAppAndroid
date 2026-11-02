package com.zinemaapp.ui.uistate

import com.zinemaapp.core.error.Error

data class UiState(
    val isLoading : Boolean = false,
    val error : Error? = null
)