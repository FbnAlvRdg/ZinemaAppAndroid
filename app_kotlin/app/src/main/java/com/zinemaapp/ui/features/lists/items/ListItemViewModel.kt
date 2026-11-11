package com.zinemaapp.ui.features.lists.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinemaapp.domain.model.ListItemResponse
import com.zinemaapp.domain.usecase.lists.AddItemsUseCase
import com.zinemaapp.domain.usecase.lists.DeleteItemUseCase
import com.zinemaapp.domain.usecase.lists.GetItemsUseCase
import com.zinemaapp.ui.uistate.UiState
import com.zinemaapp.core.error.mapper.toAppError
import com.zinemaapp.data.remote.dto.lists.AddItemRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = e.toAppError()
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

    fun clearError() {
        uiState = uiState.copy(
            error = null
        )
    }

    fun clearConfirmation() {
        confirmation = null
    }
}