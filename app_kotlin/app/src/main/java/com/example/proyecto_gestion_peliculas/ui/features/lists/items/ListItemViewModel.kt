package com.example.proyecto_gestion_peliculas.ui.features.lists.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.domain.model.ListItemResponse
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.AddItemsUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.DeleteItemUseCase
import com.example.proyecto_gestion_peliculas.domain.usecase.lists.GetItemsUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import com.example.proyecto_gestion_peliculas.core.error.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ListItemViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val addItemsUseCase: AddItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    var header = "Contenido"
    var items by mutableStateOf<List<ListItemResponse>>(emptyList())
        private set
    var confirmation by mutableStateOf<String?>(null)
        private set
    var uiState by mutableStateOf(UiState())
        private set

    fun loadItems(listId: Long) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                val result = getItemsUseCase(listId)
                items = result
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

    fun addItem(listId: Long, tmdbId: Long, type: String, title: String, poster: String?) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                val addItemRequest = AddItemRequest(
                    tmdbId = tmdbId,
                    type = type,
                    title = title,
                    poster = poster
                )
                addItemsUseCase(listId, addItemRequest)
                loadItems(listId)
                confirmation = "Se ha guardado en la lista"
            } catch (e: IOException) {
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
                confirmation = null
            } catch (e: HttpException) {
                uiState = uiState.copy(
                    error = when (e.code()) {
                        404 -> Error.NOT_FOUND
                        409 -> Error.CONFLICT
                        in 500..599 -> Error.SERVER_ERROR
                        else -> Error.UNKNOWN
                    }
                )
                confirmation = null
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = Error.UNKNOWN
                )
                confirmation = null
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun deleteItem(listId: Long, itemId: Long) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                deleteItemUseCase(listId, itemId)
                loadItems(listId)

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

    fun clearError() {
        uiState = uiState.copy(
            error = null
        )
    }

    fun clearConfirmation() {
        confirmation = null
    }
}