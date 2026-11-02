package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId: Long, itemId: Long): Boolean {
        return repository.deleteItem(listId, itemId)
    }
}