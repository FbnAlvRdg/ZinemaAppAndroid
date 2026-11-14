package com.zinemaapp.zinemaapp.presentation.dto.signup;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class RegisterRequestValidationTest {
    private Validator validator;

    @BeforeEach
    void prepareValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validDataPassesValidation() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("ejemplo");
        registerRequestDTO.setEmail("ejemplo@test.com");
        registerRequestDTO.setPassword("1234567890");

        Set<ConstraintViolation<RegisterRequestDTO>> errors = validator.validate(registerRequestDTO);

        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    void emptyRequiredFields_failValidation() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("");
        registerRequestDTO.setEmail("");
        registerRequestDTO.setPassword("");

        Set<ConstraintViolation<RegisterRequestDTO>> errors = validator.validate(registerRequestDTO);
        Assertions.assertFalse(errors.isEmpty());
    }

    @Test
    void usernameTooLong_failValidation(){
        String username = "a".repeat(50);

        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername(username);
        registerRequestDTO.setEmail("ejemplo@test.com");
        registerRequestDTO.setPassword("1234567890");

        Set<ConstraintViolation<RegisterRequestDTO>> errors = validator.validate(registerRequestDTO);
        Assertions.assertFalse(errors.isEmpty());
    }
}
