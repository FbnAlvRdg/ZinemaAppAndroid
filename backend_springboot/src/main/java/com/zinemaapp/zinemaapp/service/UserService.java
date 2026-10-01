package com.zinemaapp.zinemaapp.service;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.RegisterRequestDTO;
import com.zinemaapp.zinemaapp.presentation.dto.signup.UserResponseDTO;
import com.zinemaapp.zinemaapp.presentation.dto.login.LoginResponseDTO;
import com.zinemaapp.zinemaapp.infrastructure.repository.UserRepository;
import com.zinemaapp.zinemaapp.infrastructure.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El email ya se encuentra registrado"
            );
        }

        if (userRepository.existsByUsername(registerRequestDTO.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El nombre de usuario ya se encuentra registrado"
            );
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerRequestDTO.getEmail());
        userEntity.setUsername(registerRequestDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        UserEntity userEntitySaved = userRepository.save(userEntity);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntitySaved.getId());
        userResponseDTO.setEmail(userEntitySaved.getEmail());
        userResponseDTO.setUsername(userEntitySaved.getUsername());

        return userResponseDTO;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "El email no es correcto"
            );
        }

        UserEntity userEntityToResponse = user.get();

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userEntityToResponse.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "La contraseña no es correcta"
            );
        }

        String token = jwtService.generateToken(userEntityToResponse);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntityToResponse.getId());
        userResponseDTO.setUsername(userEntityToResponse.getUsername());
        userResponseDTO.setEmail(userEntityToResponse.getEmail());

        return new LoginResponseDTO(token, userResponseDTO);
    }

    public UserResponseDTO getCurrentUser(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("El email no se ha encontrado");
        }

        UserEntity userEntity = userOptional.get();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntity.getId());
        userResponseDTO.setEmail(userEntity.getEmail());
        userResponseDTO.setUsername(userEntity.getUsername());

        return userResponseDTO;
    }
}
