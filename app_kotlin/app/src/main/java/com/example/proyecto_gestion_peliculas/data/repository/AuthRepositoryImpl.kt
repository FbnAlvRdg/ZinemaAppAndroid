package com.example.proyecto_gestion_peliculas.data.repository

import com.example.proyecto_gestion_peliculas.data.remote.datasource.auth.AuthDataSource
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.LoginResponseDTO
import com.example.proyecto_gestion_peliculas.data.remote.dto.auth.RegisterRequestDTO
import com.example.proyecto_gestion_peliculas.data.remote.mapper.toDomain
import com.example.proyecto_gestion_peliculas.domain.model.LoginResponse
import com.example.proyecto_gestion_peliculas.domain.model.User
import com.example.proyecto_gestion_peliculas.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource) :
    AuthRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): User {

        val request = RegisterRequestDTO(
            username = username,
            email = email,
            password = password
        )

        return dataSource.register(request).toDomain()
    }

    override suspend fun login(
        email: String,
        password: String
    ): LoginResponse {

        val request = LoginRequestDTO(
            email = email,
            password = password
        )

        return dataSource.login(request).toDomain()
    }

    override suspend fun me(): User {
        return dataSource.me().toDomain()
    }
}