package com.app.backend.service;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(User user){
        Optional<User> usernameExists = userRepository.findByUsername(user.getUsername());
        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());

        if (usernameExists.isPresent()){
            return "Username exists";
        }
        if (emailExists.isPresent()){
            return "Email exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "Success!";
    }

    public String login(User user){
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if (userExists.isEmpty()){
            return "Failed authentication";
        }

        User savedUser = userExists.get();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        savedUser.getUsername(),
                        user.getPassword()
                )
        );

        return "Success!";
    }
}
