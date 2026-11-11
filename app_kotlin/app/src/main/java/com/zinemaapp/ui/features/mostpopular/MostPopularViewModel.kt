package com.zinemaapp.ui.features.mostpopular

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.zinemaapp.domain.model.Film
import com.zinemaapp.domain.model.TvSerie
import com.zinemaapp.domain.usecase.film.GetMostPopularFilmsUseCase
import com.zinemaapp.domain.usecase.tvserie.GetMostPopularTvSeriesUseCase
import com.zinemaapp.ui.uistate.UiState
import com.zinemaapp.core.error.mapper.toAppError
import com.zinemaapp.data.datastore.clearJwt
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getMostPopularFilmsUseCase: GetMostPopularFilmsUseCase,
    private val getMostPopularSeriesUseCase: GetMostPopularTvSeriesUseCase
) : ViewModel() {

    var header = "Populares"
    var selectedTab by mutableIntStateOf(0)
        private set
    var films by mutableStateOf<Flow<PagingData<Film>>?>(emptyFlow())
        private set
    var series by mutableStateOf<Flow<PagingData<TvSerie>>?>(emptyFlow())
        private set
    var uiState by mutableStateOf(UiState())
        private set

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun getMostPopularFilms() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                films = getMostPopularFilmsUseCase()
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

    fun getMostPopularSeries() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                series = getMostPopularSeriesUseCase()
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

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }

    fun cleanError() {
        uiState = uiState.copy(
            error = null
        )
    }
}