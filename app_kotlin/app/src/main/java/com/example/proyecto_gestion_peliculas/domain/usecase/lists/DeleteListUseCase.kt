package com.example.proyecto_gestion_peliculas.domain.usecase.lists

import com.example.proyecto_gestion_peliculas.domain.repository.ListsRepository
import javax.inject.Inject

class DeleteListUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId: Long): Boolean {
        return repository.deleteList(listId)
    }
}