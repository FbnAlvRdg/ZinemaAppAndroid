package com.zinemaapp.data.repository

import com.zinemaapp.data.remote.datasource.auth.AuthDataSource
import com.zinemaapp.data.remote.dto.auth.LoginRequestDTO
import com.zinemaapp.data.remote.dto.auth.LoginResponseDTO
import com.zinemaapp.data.remote.dto.auth.RegisterRequestDTO
import com.zinemaapp.data.remote.dto.auth.UserResponseDTO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthRepositoryImplTest {
    private lateinit var dataSource: FakeAuthDataSource
    private lateinit var repository: AuthRepositoryImpl

    @Before
    fun prepareTest() {
        dataSource = FakeAuthDataSource()
        repository = AuthRepositoryImpl(dataSource)
    }

    @Test
    fun register_trimsUsernameAndEmail_butNotPassword() {
        runBlocking {
            repository.register(
                " usuario ",
                " usuario@test.com ",
                " 1234567890 "
            )
        }

        val request = dataSource.registerRequest

        assertEquals("usuario", request?.username)
        assertEquals("usuario@test.com", request?.email)
        assertEquals(" 1234567890 ", request?.password)
    }

    @Test
    fun login_trimsEmail_butNotPassword() {
        runBlocking {
            repository.login(
                " usuario@test.com ",
                " 1234567890 "
            )

            val request = dataSource.loginRequest

            assertEquals("usuario@test.com", request?.email)
            assertEquals(" 1234567890 ", request?.password)
        }
    }

    private class FakeAuthDataSource : AuthDataSource {
        var registerRequest: RegisterRequestDTO? = null
        var loginRequest: LoginRequestDTO? = null

        override suspend fun register(registerRequestDTO: RegisterRequestDTO): UserResponseDTO {
            registerRequest = registerRequestDTO

            return UserResponseDTO(
                id = 1,
                username = "usuario",
                email = "usuario@test.com"
            )
        }

        override suspend fun login(loginRequestDTO: LoginRequestDTO): LoginResponseDTO {
            loginRequest = loginRequestDTO

            return LoginResponseDTO(
                token = "token-de-prueba",
                user = UserResponseDTO(
                    id = 1,
                    username = "usuario",
                    email = "usuario@test.com"
                )
            )
        }
    }
}