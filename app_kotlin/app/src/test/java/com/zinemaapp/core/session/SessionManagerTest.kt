package com.zinemaapp.core.session

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.Assert.assertTrue

class SessionManagerTest {

    @After
    fun limpiarEstado(){
        SessionManager.consumeSessionExpired()
    }

    @Test
    fun `notificar expiracion cambiado el estado a true`(){
        SessionManager.notifySessionExpired()
        assertTrue(SessionManager.sessionExpired.value)
    }

    @Test
    fun `consumir expiracion cambiando el estado a false`(){
        SessionManager.notifySessionExpired()

        SessionManager.consumeSessionExpired()

        assertFalse(SessionManager.sessionExpired.value)
    }
}