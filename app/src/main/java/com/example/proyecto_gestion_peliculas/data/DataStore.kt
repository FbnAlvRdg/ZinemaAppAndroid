package com.example.proyecto_gestion_peliculas.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("last_email")
val SAVED_EMAIL = stringPreferencesKey("last_email")
suspend fun saveEmail(context: Context, email: String) {
    context.dataStore.edit { preferences ->
        preferences[SAVED_EMAIL] = email
    }
}

suspend fun readEmail(context: Context): String {
    val dataFlow = context.dataStore.data
    val preferences = dataFlow.first()
    val savedEmail = preferences[SAVED_EMAIL]

    if (savedEmail != null) {
        return savedEmail
    } else {
        return ""
    }
}