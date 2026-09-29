package com.example.proyecto_gestion_peliculas.ui.features.mostpopular

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.data.datastore.clearJwt
import com.example.proyecto_gestion_peliculas.domain.model.Film
import com.example.proyecto_gestion_peliculas.domain.model.TvSerie
import com.example.proyecto_gestion_peliculas.domain.usecase.film.GetMostPopularFilmsUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.tvserie.GetMostPopularTvSeriesUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
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
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        404 -> Error.NOT_FOUND
                        in 500..599 -> Error.SERVER_ERROR
                        else -> Error.UNKNOWN
                    }
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = Error.UNKNOWN
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
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        404 -> Error.NOT_FOUND
                        in 500..599 -> Error.SERVER_ERROR
                        else -> Error.UNKNOWN
                    }
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    error = Error.UNKNOWN
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