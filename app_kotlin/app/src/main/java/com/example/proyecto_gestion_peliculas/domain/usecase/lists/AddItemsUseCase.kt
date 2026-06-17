package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.data.remote.dto.lists.AddItemRequest
import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class AddItemsUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId : Long, addItemRequest: AddItemRequest) : Boolean {
        return repository.addItems(listId, addItemRequest)
    }
}