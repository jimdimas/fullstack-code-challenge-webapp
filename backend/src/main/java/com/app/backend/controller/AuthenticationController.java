package com.app.backend.controller;

import com.app.backend.exception.CustomException;
import com.app.backend.model.AuthResponse;
import com.app.backend.model.User;
import com.app.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) throws CustomException {
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) throws CustomException {
        return authenticationService.login(user);
    }
}
