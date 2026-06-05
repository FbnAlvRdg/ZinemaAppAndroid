package com.example.proyecto_gestion_peliculas.ui.features.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.data.datastore.saveJwt
import com.example.proyecto_gestion_peliculas.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginSuccess by mutableStateOf(false)

    fun enterEmail(enteredEmail: String) {
        email = enteredEmail
    }

    fun enterPassword(enteredPassword: String) {
        password = enteredPassword
    }

    fun login() {
        viewModelScope.launch {
            try {
                val result = loginUseCase(email, password)
                saveJwt(context, result.token)
                loginSuccess = true
            } catch (e: Exception) {
                loginSuccess = false
                e.printStackTrace()
            }
        }
    }
}