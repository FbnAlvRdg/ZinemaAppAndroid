package com.example.proyecto_gestion_peliculas.ui.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set
    fun enterEmail(enteredEmail: String){
        email = enteredEmail
    }
    fun enterPassword(enteredPassword : String) {
        password = enteredPassword
    }
}