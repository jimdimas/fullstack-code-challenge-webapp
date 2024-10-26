package com.app.backend.controller;

import com.app.backend.exception.CustomException;
import com.app.backend.model.AuthResponse;
import com.app.backend.model.ResetPasswordDataWrapper;
import com.app.backend.model.TokenDataWrapper;
import com.app.backend.model.User;
import com.app.backend.service.AuthenticationService;
import com.app.backend.service.JsonBody;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<JsonBody> register(@RequestBody User user) throws CustomException, MessagingException {
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) throws CustomException {
        return authenticationService.login(user);
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<JsonBody> verifyEmail(@RequestBody TokenDataWrapper token){
        return authenticationService.verifyEmail(token.getToken());
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<JsonBody> forgotPassword(@RequestBody User user) throws MessagingException {
        return authenticationService.forgotPassword(user.getEmail());
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<JsonBody> resetPassword(@RequestBody ResetPasswordDataWrapper data){
        return authenticationService.resetPassword(data.getToken(), data.getPassword());
    }
}
