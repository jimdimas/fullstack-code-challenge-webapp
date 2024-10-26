package com.app.backend.service;

import com.app.backend.config.EmailConfiguration;
import com.app.backend.exception.CustomException;
import com.app.backend.model.*;
import com.app.backend.repository.StudentRepository;
import com.app.backend.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.app.backend.util.MailUtil.sendEmailVerificationMail;
import static com.app.backend.util.MailUtil.sendPasswordResetMail;
import static com.app.backend.util.SecurityUtils.createVerificationToken;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final EmailConfiguration emailConfiguration;

    @Transactional
    public ResponseEntity<JsonBody> register(User user) throws CustomException, MessagingException {
        Optional<User> usernameExists = userRepository.findByUsername(user.getUsername());
        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());

        if (usernameExists.isPresent()){
            throw new CustomException("Username exists");
        }
        if (emailExists.isPresent()){
            throw new CustomException("Email exists");
        }

        String verificationToken = createVerificationToken();
        Student student = Student.builder().
                username(user.getUsername()).
                password(passwordEncoder.encode(user.getPassword())).
                verificationToken(verificationToken).
                tokenExpirationDate(LocalDateTime.now().plusHours(1)).
                tokenType(TokenType.EMAIL).
                email(user.getEmail()).
                role("STUDENT").
                level("Beginner").
                ranking(0).
                build();
        studentRepository.save(student);

        sendEmailVerificationMail(
                emailConfiguration,
                user.getEmail(),
                verificationToken
        );
        return ResponseEntity.ok(JsonBody.builder().message("You need to verify your email to login.").build());
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

    public ResponseEntity<JsonBody> verifyEmail(String verificationToken){
        Optional<User> userExists = userRepository.findByVerificationToken(verificationToken);
        if (userExists.isEmpty()){
            throw new CustomException("Email verification failed");
        }

        User user = userExists.get();
        if (user.getTokenType().equals(TokenType.PASSWORD)){
            throw new CustomException("Email verification failed");
        }
        if (user.getTokenType().equals(TokenType.EMAIL) &&
                user.getTokenExpirationDate().isBefore(LocalDateTime.now())){
            throw new CustomException("Email verification link has expired.");
        }

        user.setVerificationToken(null);
        user.setTokenExpirationDate(null);
        user.setTokenType(null);
        userRepository.save(user);
        return ResponseEntity.ok(
                JsonBody.builder().message("Email verification was successful,login to continue.").build());
    }

    @Transactional
    public ResponseEntity<JsonBody> forgotPassword(String email) throws MessagingException {
        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isEmpty()){
            throw new CustomException("User with given email doesn't exist.");
        }
        User user = userExists.get();
        if (user.getTokenType().equals(TokenType.EMAIL) && !user.isEnabled()){
            throw new CustomException("You need to verify your email address first in order to reset your password.");
        }
        String token = createVerificationToken();
        user.setVerificationToken(token);
        user.setTokenExpirationDate(LocalDateTime.now().plusHours(1));
        user.setTokenType(TokenType.PASSWORD);
        userRepository.save(user);
        sendPasswordResetMail(
                emailConfiguration,
                email,
                token
        );
        return ResponseEntity.ok(JsonBody.builder().message("Check your email in order to reset your password.").build());
    }

    public ResponseEntity<JsonBody> resetPassword(String token,String password){
        Optional<User> userExists = userRepository.findByVerificationToken(token);
        if (userExists.isEmpty()){
            throw new CustomException("Password reset failed,try again.");
        }
        User user = userExists.get();
        if (!user.getTokenType().equals(TokenType.PASSWORD)){
            throw new CustomException("Password reset failed,try again.");
        }
        if (user.getTokenExpirationDate().isBefore(LocalDateTime.now())){
            throw new CustomException("Password reset failed,try again.");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setVerificationToken(null);
        user.setTokenExpirationDate(null);
        user.setTokenType(null);
        userRepository.save(user);
        return ResponseEntity.ok(JsonBody.builder().message("Password reset was successful,login to continue.").build());
    }
}
