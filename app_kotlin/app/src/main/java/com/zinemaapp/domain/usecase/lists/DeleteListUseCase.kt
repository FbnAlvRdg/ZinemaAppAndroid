package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class DeleteListUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId: Long): Boolean {
        return repository.deleteList(listId)
    }
}