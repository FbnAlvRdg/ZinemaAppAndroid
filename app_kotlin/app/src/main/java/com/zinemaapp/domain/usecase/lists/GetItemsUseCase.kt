package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.domain.model.ListItemResponse
import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class GetItemsUseCase  @Inject constructor(private val repository: ListsRepository){
    suspend operator fun invoke(listId : Long) : List<ListItemResponse>{
        return repository.getItems(listId)
    }
}