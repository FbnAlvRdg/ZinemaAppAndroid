package com.zinemaapp.core.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object SessionManager {
    private val _sessionExpired = MutableStateFlow(false)
    val sessionExpired = _sessionExpired.asStateFlow()

    fun notifySessionExpired() {
        _sessionExpired.value = true
    }

    fun consumeSessionExpired() {
        _sessionExpired.value = false
    }
}