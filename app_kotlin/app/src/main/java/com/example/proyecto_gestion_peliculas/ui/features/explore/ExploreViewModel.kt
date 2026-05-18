package com.example.proyecto_gestion_peliculas.ui.features.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    var selectedTab by mutableIntStateOf(0)
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }
}