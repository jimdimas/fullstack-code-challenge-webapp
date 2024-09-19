package com.app.backend.service;

import com.app.backend.model.Student;
import com.app.backend.model.Supervisor;
import com.app.backend.model.User;
import com.app.backend.repository.StudentRepository;
import com.app.backend.repository.SupervisorRepository;
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
    private final SupervisorRepository supervisorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

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

        if (user.getEmail().contains("supervisor")){
            user.setRole("SUPERVISOR");
            Supervisor supervisor= new Supervisor();
            supervisor.setEmail(user.getEmail());
            supervisor.setPassword(user.getPassword());
            supervisor.setUsername(user.getUsername());
            supervisor.setRole(user.getRole());
            supervisorRepository.save(supervisor);
        } else {
            Student student = new Student();
            student.setSchool("University");
            student.setRole("STUDENT");
            student.setPassword(user.getPassword());
            student.setEmail(user.getEmail());
            student.setUsername(user.getUsername());
            studentRepository.save(student);
        }

        String token = jwtService.generateToken(user);
        return token;
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

        String token = jwtService.generateToken(user);
        return token;
    }
}
