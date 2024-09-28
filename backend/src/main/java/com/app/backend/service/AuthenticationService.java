package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.*;
import com.app.backend.repository.UserRepository;
import com.app.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponse register(User user) throws CustomException {
        Optional<User> usernameExists = userRepository.findByUsername(user.getUsername());
        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());

        if (usernameExists.isPresent()){
            throw new CustomException("Username exists");
        }
        if (emailExists.isPresent()){
            throw new CustomException("Email exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userWithRole = userUtil.setupUserRegister(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).user(userWithRole).build();
    }

    public AuthResponse login(User user) throws CustomException {
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if (userExists.isEmpty()) {
            throw new CustomException("Bad credentials");
        }

        User savedUser = userExists.get();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        savedUser.getUsername(),
                        user.getPassword()
                )
        );

        String token = jwtService.generateToken(savedUser);
        return AuthResponse.builder().token(token).user(savedUser).build();
    }
}
