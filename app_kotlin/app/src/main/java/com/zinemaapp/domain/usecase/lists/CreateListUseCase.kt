package com.zinemaapp.domain.usecase.lists

import com.zinemaapp.data.remote.dto.lists.CreateListRequest
import com.zinemaapp.domain.model.ListResponse
import com.zinemaapp.domain.repository.ListsRepository
import javax.inject.Inject

class CreateListUseCase @Inject constructor(private val repository: ListsRepository) {
    suspend operator fun invoke(createListRequest: CreateListRequest) : ListResponse {
        return repository.createList(createListRequest)
    }
}