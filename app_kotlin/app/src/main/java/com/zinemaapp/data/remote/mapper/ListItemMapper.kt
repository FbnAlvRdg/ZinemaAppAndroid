package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.lists.ListItemResponseDTO
import com.zinemaapp.domain.model.ListItemResponse
import java.util.Locale.getDefault


fun ListItemResponseDTO.toDomain(): ListItemResponse {
    return ListItemResponse(
        id = id,
        tmdbId = tmdbId,
        type = type.lowercase(getDefault()),
        title = title,
        poster = poster
    )
}