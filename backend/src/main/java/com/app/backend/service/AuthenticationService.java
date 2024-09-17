package com.app.backend.service;

import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final JWTService jwtService;

    public String register(User user, HttpServletResponse response){
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
        String token = jwtService.generateToken(user);
        Cookie tokenCookie = new Cookie("token",token);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
        return "Success!";
    }

    public String login(User user,HttpServletResponse response){
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

        String token = jwtService.generateToken(user);
        Cookie tokenCookie = new Cookie("token",token);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
        return "Success!";
    }
}
