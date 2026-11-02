package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.data.remote.dto.lists.AddItemRequest
import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class AddItemsUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(listId : Long, addItemRequest: AddItemRequest) : Boolean {
        return repository.addItems(listId, addItemRequest)
    }
}