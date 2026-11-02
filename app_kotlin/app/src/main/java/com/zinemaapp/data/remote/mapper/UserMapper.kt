package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.auth.UserResponseDTO
import com.zinemaapp.domain.model.User

fun UserResponseDTO.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email
    )
}