package com.example.proyecto_gestion_peliculas.ui.screens.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {
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