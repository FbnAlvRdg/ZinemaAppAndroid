package com.zinemaapp.data.remote.datasource.auth

import com.zinemaapp.data.remote.api.AuthApi
import com.zinemaapp.data.remote.dto.auth.LoginRequestDTO
import com.zinemaapp.data.remote.dto.auth.LoginResponseDTO
import com.zinemaapp.data.remote.dto.auth.RegisterRequestDTO
import com.zinemaapp.data.remote.dto.auth.UserResponseDTO


import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val api: AuthApi) : AuthDataSource {
    override suspend fun register(registerRequestDTO: RegisterRequestDTO): UserResponseDTO {
        return api.register(registerRequestDTO)
    }

    override suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO {
        return api.login(loginRequestDTO)
    }
}