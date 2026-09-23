package com.example.proyecto_gestion_peliculas.ui.features.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_gestion_peliculas.core.error.Error
import com.example.proyecto_gestion_peliculas.data.datastore.saveJwt
import com.example.proyecto_gestion_peliculas.domain.usecase.auth.LoginUseCase
import com.example.proyecto_gestion_peliculas.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val title = "ZinemaApp"
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var loginSuccess by mutableStateOf(false)
    var uiState by mutableStateOf(UiState())
        private set

    fun enterEmail(enteredEmail: String) {
        email = enteredEmail
    }

    fun enterPassword(enteredPassword: String) {
        password = enteredPassword
    }

    fun login() {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(
                    isLoading = true
                )
                val result = loginUseCase(email, password)
                saveJwt(context, result.token)
                loginSuccess = true
            } catch (e: IOException) {
                loginSuccess = false
                uiState = uiState.copy(
                    error = Error.CONNECTION_ERROR
                )
            } catch (e: HttpException) {
                loginSuccess = false
                uiState = uiState.copy(
                    error = when (e.code()) {
                        401, 403 -> Error.INVALID_CREDENTIALS
                        in 500..599 -> Error.CONNECTION_ERROR
                        else -> Error.UNKNOWN
                    }
                )
            } catch (e: Exception) {
                loginSuccess = false
                uiState = uiState.copy(
                    error = Error.UNKNOWN
                )
            } finally {
                uiState = uiState.copy(
                    isLoading = false
                )
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(
            error = null
        )
    }
}