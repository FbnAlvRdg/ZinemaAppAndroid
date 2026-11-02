package com.zinemaapp.domain.usecase.auth

import com.zinemaapp.domain.model.User
import com.zinemaapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository : AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String) : User {
        return repository.register(username, email, password)
    }
}