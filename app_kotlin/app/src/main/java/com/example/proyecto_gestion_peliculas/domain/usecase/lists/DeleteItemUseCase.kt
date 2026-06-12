package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId: Long, itemId: Long): Boolean {
        return repository.deleteItem(listId, itemId)
    }
}