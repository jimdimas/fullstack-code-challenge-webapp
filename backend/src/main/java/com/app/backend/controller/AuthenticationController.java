package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletResponse response){
        return authenticationService.register(user,response);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response){
        return authenticationService.login(user,response);
    }
}
