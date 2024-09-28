package com.app.backend.util;

import com.app.backend.model.Admin;
import com.app.backend.model.Student;
import com.app.backend.model.Supervisor;
import com.app.backend.model.User;
import com.app.backend.repository.AdminRepository;
import com.app.backend.repository.StudentRepository;
import com.app.backend.repository.SupervisorRepository;
import com.app.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserUtil {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final SupervisorRepository supervisorRepository;
    private final StudentRepository studentRepository;
    public User setupUserRegister(User user) {
        if (user.getEmail().contains("admin")) {
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
        } else if (user.getEmail().contains("supervisor")) {
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
