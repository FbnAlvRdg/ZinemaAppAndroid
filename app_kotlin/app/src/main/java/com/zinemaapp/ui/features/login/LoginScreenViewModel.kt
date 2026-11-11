package com.zinemaapp.ui.features.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zinemaapp.data.datastore.saveJwt
import com.zinemaapp.domain.usecase.auth.LoginUseCase
import com.zinemaapp.ui.uistate.UiState
import com.zinemaapp.core.error.Error
import com.zinemaapp.core.error.mapper.toAppError
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @param:ApplicationContext private val context: Context
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

            } catch (e: Exception) {
                loginSuccess = false

                val mappedError = e.toAppError()

                uiState = uiState.copy(
                    error = when (mappedError) {
                        Error.UNAUTHORIZED -> Error.INVALID_CREDENTIALS
                        else -> mappedError
                    }
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