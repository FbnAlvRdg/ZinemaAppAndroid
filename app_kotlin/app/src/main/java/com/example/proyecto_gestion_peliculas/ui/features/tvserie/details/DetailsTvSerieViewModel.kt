package com.example.proyecto_gestion_peliculas.ui.features.tvserie.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetSerieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsTvSerieViewModel @Inject constructor(private val getSerieByIdUseCase: GetSerieByIdUseCase) :
    ViewModel() {

    var header = "Detalles"
    var serie by mutableStateOf<TvSerie?>(null)
        private set

    fun loadSerie(id : Int){
        viewModelScope.launch {
            serie = getSerieByIdUseCase(id)
        }
    }
}