package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.*;
import com.app.backend.repository.AdminRepository;
import com.app.backend.repository.StudentRepository;
import com.app.backend.repository.SupervisorRepository;
import com.app.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final SupervisorRepository supervisorRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
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
        User userWithRole = setupUserRegister(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).user(userWithRole).build();
    }

    public AuthResponse login(User user) throws CustomException {
        Optional<User> userExists = userRepository.findByUsername(user.getUsername());
        if (userExists.isEmpty()){
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

    private User setupUserRegister(User user){
        if (user.getEmail().contains("admin")){
            user.setRole("ADMIN");
            Admin admin = Admin.builder().
                    username(user.getUsername()).
                    password(user.getPassword()).
                    email(user.getEmail()).
                    role("ADMIN").
                    createdAt(new Date()).
                    license("COMMON").
                    build();
            adminRepository.save(admin);
        } else if (user.getEmail().contains("supervisor")){
            user.setRole("SUPERVISOR");
            Supervisor supervisor = Supervisor.builder().
                    username(user.getUsername()).
                    password(user.getPassword()).
                    email(user.getEmail()).
                    role("SUPERVISOR").
                    organization("University").
                    expertise("Coding").
                    build();
            supervisorRepository.save(supervisor);
        } else {
            user.setRole("STUDENT");
            Student student = Student.builder().
                    username(user.getUsername()).
                    password(user.getPassword()).
                    email(user.getEmail()).
                    role("STUDENT").
                    level("Beginner").
                    ranking(0).
                    build();
            studentRepository.save(student);
        }
        return user;
    }
}
