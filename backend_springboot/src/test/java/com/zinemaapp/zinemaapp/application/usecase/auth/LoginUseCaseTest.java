package com.zinemaapp.zinemaapp.application.usecase.auth;

import com.zinemaapp.zinemaapp.domain.model.auth.LoginRequest;
import com.zinemaapp.zinemaapp.domain.model.auth.LoginResponse;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.domain.repository.user.UserRepository;
import com.zinemaapp.zinemaapp.infrastructure.security.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class LoginUseCaseTest {
    private FakeUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private FakeJwtService jwtService;
    private LoginUseCase loginUseCase;

    @BeforeEach
    void prepareTest() {
        userRepository = new FakeUserRepository();
        passwordEncoder = new BCryptPasswordEncoder();
        jwtService = new FakeJwtService();
        loginUseCase = new LoginUseCase(
                userRepository,
                passwordEncoder,
                jwtService
        );
    }

    @Test
    void emailNotFound_throwsUnauthorized() {
        LoginRequest loginRequest = new LoginRequest(
                "test@test.es",
                "1234567890"
        );

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> loginUseCase.login(loginRequest));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
    }

    @Test
    void incorrectPassword_throwsUnauthorized() {
        userRepository.user = createUser();

        LoginRequest loginRequest = new LoginRequest("test@test.com", "wrong-password");

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> loginUseCase.login(loginRequest));

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());

    }

    @Test
    void validCredentials_returnsTokenAndUser() {
        userRepository.user = createUser();

        LoginRequest loginRequest = new LoginRequest(
                "test@test.com",
                "1234567890"
        );

        LoginResponse result = loginUseCase.login(loginRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("token-de-prueba", result.getToken());
        Assertions.assertNotNull(result.getUser());
        Assertions.assertEquals("test@test.com", result.getUser().getEmail());
    }

    private static class FakeUserRepository implements UserRepository {
        private User user;

        @Override
        public Optional<User> findByEmail(String email) {
            return Optional.ofNullable(user);
        }

        @Override
        public boolean existsByEmail(String email) {
            return false;
        }

        @Override
        public boolean existsByUsername(String username) {
            return false;
        }

        @Override
        public User save(User user) {
            return null;
        }
    }

    private static class FakeJwtService extends JwtService {
        public String generateToken(String email) {
            return "token-de-prueba";
        }
    }

    private User createUser() {
        String encodedPassword = passwordEncoder.encode("1234567890");
        User user = new User(
                1L,
                "test@test.com",
                "test-user",
                encodedPassword
        );

        return user;
    }
}
