package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.lists.ListResponseDTO
import com.zinemaapp.domain.model.ListResponse


fun ListResponseDTO.toDomain() : ListResponse {
    return ListResponse(
        id = id,
        name = name
    )
}