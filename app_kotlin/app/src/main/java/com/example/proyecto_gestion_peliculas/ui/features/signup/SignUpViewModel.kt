package com.example.proyecto_gestion_peliculas.ui.features.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    var username by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var phone by mutableStateOf("")
        private set
    var checkedRememberMe by mutableStateOf(false)
        private set
    var checkedTerms by mutableStateOf(false)
        private set

    fun onUsernameChange(value: String) {
        username = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
    }

    fun onPhoneChange(value: String) {
        phone = value
    }

    fun onCheckedRememberMeChange() {
        checkedRememberMe = !checkedRememberMe
    }

    fun onCheckedTermsChange() {
        checkedTerms = !checkedTerms
    }

    fun register() {
        viewModelScope.launch {
            registerUseCase(username, email, password)
        }
    }
}