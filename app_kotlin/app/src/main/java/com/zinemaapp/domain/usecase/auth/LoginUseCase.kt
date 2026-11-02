package com.zinemaapp.domain.usecase.auth

import com.zinemaapp.domain.model.LoginResponse
import com.zinemaapp.domain.model.User
import com.zinemaapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email : String, password : String) : LoginResponse {
        return repository.login(email, password)
    }
}