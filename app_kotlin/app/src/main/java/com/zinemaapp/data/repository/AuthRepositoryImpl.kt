package com.zinemaapp.data.repository

import com.zinemaapp.data.remote.datasource.auth.AuthDataSource
import com.zinemaapp.data.remote.dto.auth.LoginRequestDTO
import com.zinemaapp.data.remote.dto.auth.RegisterRequestDTO
import com.zinemaapp.data.remote.mapper.toDomain
import com.zinemaapp.domain.model.LoginResponse
import com.zinemaapp.domain.model.User
import com.zinemaapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource) :
    AuthRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): User {

        val request = RegisterRequestDTO(
            username = username.trim(),
            email = email.trim(),
            password = password
        )

        return dataSource.register(request).toDomain()
    }

    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {

        val request = LoginRequestDTO(
            email = email.trim(),
            password = password
        )

        return dataSource.login(request).toDomain()
    }
}