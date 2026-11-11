package com.zinemaapp.ui.features.toprated

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
import com.zinemaapp.domain.usecase.film.GetTopRatedFilmsUseCase
import com.zinemaapp.domain.usecase.tvserie.GetTopRatedSeriesPagingUseCase
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
class TopRatedViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val getTopRatedFilmsUseCase: GetTopRatedFilmsUseCase,
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesPagingUseCase
) : ViewModel() {

    var header = "Mejor Valoradas"
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

    fun getTopRatedFilms() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                films = getTopRatedFilmsUseCase()
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

    fun getTopRatedSeries() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                series = getTopRatedSeriesUseCase()

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

    fun cleanError() {
        uiState = uiState.copy(
            error = null
        )
    }

    fun logOut(onFinished: () -> Unit) {
        viewModelScope.launch {
            clearJwt(context)
            onFinished()
        }
    }
}