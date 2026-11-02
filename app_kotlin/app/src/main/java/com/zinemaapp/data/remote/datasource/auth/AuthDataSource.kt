package com.zinemaapp.data.remote.datasource.auth

import com.zinemaapp.data.remote.dto.auth.LoginRequestDTO
import com.zinemaapp.data.remote.dto.auth.LoginResponseDTO
import com.zinemaapp.data.remote.dto.auth.RegisterRequestDTO
import com.zinemaapp.data.remote.dto.auth.UserResponseDTO


interface AuthDataSource {
    suspend fun register(registerRequestDTO: RegisterRequestDTO): UserResponseDTO
    suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO
}