package com.example.proyecto_gestion_peliculas.core

import android.util.Patterns

fun checkEmail(email: String): Boolean {
    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return true
    } else {
        return false
    }
}

fun checkPassword(password: String): Boolean {
    if (password.length in 6..16) {
        return true
    } else {
        return false
    }
}

fun checkEqualPassword(password: String, password2: String): Boolean {
    return password == password2
}

