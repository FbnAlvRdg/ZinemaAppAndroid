package com.zinemaapp.data.remote.mapper

import com.zinemaapp.data.remote.dto.auth.LoginResponseDTO
import com.zinemaapp.domain.model.LoginResponse

fun LoginResponseDTO.toDomain() : LoginResponse {
    return LoginResponse(
        token = token,
        user = user.toDomain()
    )
}