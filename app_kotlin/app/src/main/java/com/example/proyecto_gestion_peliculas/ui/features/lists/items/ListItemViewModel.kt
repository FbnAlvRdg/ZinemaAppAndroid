package com.example.proyecto_gestion_peliculas.ui.features.lists.items


import android.icu.text.CaseMap
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListItemViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val addItemsUseCase: AddItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    var items by mutableStateOf<List<ListItemResponse>>(emptyList())
        private set

    var confirmation by mutableStateOf<String?>(null)

    var error by mutableStateOf<String?>(null)
        private set

    var header = "Contenido"

    fun loadItems(listId: Long) {
        viewModelScope.launch {
            val result = getItemsUseCase(listId)
            items = result
        }
    }

    fun addItem(listId: Long, tmdbId: Long, type: String, title: String, poster : String?) {
        viewModelScope.launch {
            try {
                val addItemRequest = AddItemRequest(
                    tmdbId = tmdbId,
                    type = type,
                    title = title,
                    poster = poster
                )

                addItemsUseCase(listId, addItemRequest)
                loadItems(listId)
                confirmation = "Se ha guardado en la lista"
                error = null
            } catch (e: retrofit2.HttpException) {
                error = "Este item ya está en la lista"
                confirmation = null
            }
        }
    }

    fun deleteItem(listId: Long, itemId: Long) {
        viewModelScope.launch {
            deleteItemUseCase(listId, itemId)
            loadItems(listId)
        }
    }

    fun clearError(){
        error = null
    }

    fun clearConfirmation(){
        confirmation = null
    }
}