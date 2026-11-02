package com.zinemaapp.data.remote.dto.auth

data class LoginResponseDTO(
    val token: String,
    val user: UserResponseDTO
)
