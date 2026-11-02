package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.credits.ActorDTO
import com.zinemaapp.domain.model.Actor

fun ActorDTO.toDomain(): Actor {
    return Actor(
        id = id,
        name = name,
        character = character
    )
}