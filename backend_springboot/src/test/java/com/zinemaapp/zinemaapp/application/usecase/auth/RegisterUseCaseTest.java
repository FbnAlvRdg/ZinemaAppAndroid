package com.zinemaapp.zinemaapp.application.usecase.auth;

import com.zinemaapp.zinemaapp.domain.model.auth.RegisterRequest;
import com.zinemaapp.zinemaapp.domain.model.user.User;
import com.zinemaapp.zinemaapp.domain.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class RegisterUseCaseTest {
    private FakeUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RegisterUseCase registerUseCase;

    @BeforeEach
    void prepareTest() {
        userRepository = new FakeUserRepository();
        passwordEncoder = new BCryptPasswordEncoder();
        registerUseCase = new RegisterUseCase(userRepository, passwordEncoder);
    }

    @Test
    void blankUsername_isSavedAsNull() {
        RegisterRequest registerRequest = new RegisterRequest(
                "   ",
                "example@test.com",
                "1234567890"
        );

        User result = registerUseCase.register(registerRequest);

        Assertions.assertNull(result.getUsername());
        Assertions.assertNotNull(result.getEmail());
        Assertions.assertNotEquals("1234567890", result.getPassword());
        Assertions.assertTrue(passwordEncoder.matches("1234567890", result.getPassword()));
    }

    @Test
    void existingEmail_throwsConflict() {
        userRepository.emailExits = true;

        RegisterRequest registerRequest = new RegisterRequest("example", "example@test.com", "1234567890");

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> registerUseCase.register(registerRequest));

        Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void existingUsername_throwsConflict() {
        userRepository.usernameExists = true;

        RegisterRequest registerRequest = new RegisterRequest("example", "example@test.com", "1234567890");

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> registerUseCase.register(registerRequest));

        Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

    @Test
    void validRegister_savesUser() {
        RegisterRequest registerRequest = new RegisterRequest("example", "example@test.com", "1234567890");

        User result = registerUseCase.register(registerRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(registerRequest.getUsername(), result.getUsername());
        Assertions.assertEquals(registerRequest.getEmail(), result.getEmail());
        Assertions.assertNotNull(userRepository.savedUser);
    }


    private static class FakeUserRepository implements UserRepository {
        private User savedUser;
        private boolean emailExits;
        private boolean usernameExists;

        @Override
        public Optional<User> findByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public boolean existsByEmail(String email) {
            return emailExits;
        }

        @Override
        public boolean existsByUsername(String username) {
            return usernameExists;
        }

        @Override
        public User save(User user) {
            savedUser = user;
            return user;
        }
    }
}
