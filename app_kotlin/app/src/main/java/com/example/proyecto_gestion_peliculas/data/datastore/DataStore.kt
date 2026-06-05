package com.example.proyecto_gestion_peliculas.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("app_preferences")
val SAVED_EMAIL = stringPreferencesKey("last_email")
val SAVED_JWT = stringPreferencesKey("saved_jwt")
suspend fun saveEmail(context: Context, email: String) {
    context.dataStore.edit { preferences ->
        preferences[SAVED_EMAIL] = email
    }
}

suspend fun readEmail(context: Context): String {
    val dataFlow = context.dataStore.data
    val preferences = dataFlow.first()
    val savedEmail = preferences[SAVED_EMAIL]

    return savedEmail ?: ""
}

suspend fun saveJwt(context: Context, jwt: String) {
    context.dataStore.edit { preferences ->
        preferences[SAVED_JWT] = jwt
    }
}

suspend fun readJwt(context: Context): String {
    val dataFlow = context.dataStore.data
    val preferences = dataFlow.first()
    val savedJwt = preferences[SAVED_JWT]

    return savedJwt ?: ""
}

suspend fun clearJwt(context: Context) {
    context.dataStore.edit { preferences ->
        preferences.remove(SAVED_JWT)
    }
}