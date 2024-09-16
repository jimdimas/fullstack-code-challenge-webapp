package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return authenticationService.login(user);
    }
}
