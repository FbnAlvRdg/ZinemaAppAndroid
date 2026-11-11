package com.zinemaapp.ui.features.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinemaapp.core.error.mapper.toAppError
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.usecase.film.GetFilmsByIdUseCase
import com.zinemaapp.domain.usecase.tvserie.GetSerieByIdUseCase
import com.zinemaapp.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFilmsByIdUseCase: GetFilmsByIdUseCase,
    private val getSerieByIdUseCase: GetSerieByIdUseCase
) :
    ViewModel() {

    var header = "Detalles"
    var film by mutableStateOf<Film?>(null)
        private set
    var serie by mutableStateOf<TvSerie?>(null)
        private set
    var uiState by mutableStateOf(UiState())
        private set


    fun loadFilm(id: Int) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                film = getFilmsByIdUseCase(id)

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.toAppError()
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun loadSerie(id: Int) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                serie = getSerieByIdUseCase(id)

            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = e.toAppError()
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun clearError(){
        uiState = uiState.copy(
            error = null
        )
    }
}