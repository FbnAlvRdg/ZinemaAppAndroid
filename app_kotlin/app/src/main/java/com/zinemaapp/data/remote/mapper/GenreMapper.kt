package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.credits.GenreDTO
import com.zinemaapp.domain.model.Genre

fun GenreDTO.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}