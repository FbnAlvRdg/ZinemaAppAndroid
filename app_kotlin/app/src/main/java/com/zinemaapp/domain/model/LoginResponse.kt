package com.zinemaapp.domain.model

data class LoginResponse(
    val token : String,
    val user: User
)
