package com.zinemaapp.domain.repository

import com.zinemaapp.domain.model.LoginResponse
import com.zinemaapp.domain.model.User

interface AuthRepository {
    suspend fun register(username : String, email: String, password: String): User
    suspend fun login(email: String, password: String): LoginResponse
}