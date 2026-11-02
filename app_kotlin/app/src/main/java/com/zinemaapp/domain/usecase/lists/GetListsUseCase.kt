package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.domain.model.ListResponse
import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class GetListsUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke() : List<ListResponse>{
        return repository.getLists()
    }
}