package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserByUsername(String username){
        Optional<User> userExists = userRepository.findByUsername(username);
        if (userExists.isEmpty()){
            throw new CustomException("User with given username doesn't exist");
        }
        return userExists.get();
    }
    public void createUser(User requestingUser,User user){
        if (!requestingUser.getRole().equals("ADMIN")){
            throw new CustomException("Unable to perfom action");
        }
        Optional<User> usernameExists = userRepository.findByUsername(user.getUsername());
        if (usernameExists.isPresent()){
            throw new CustomException("Username already exists");
        }
        Optional<User> emailExists = userRepository.findByEmail(user.getEmail());
        if (emailExists.isPresent()){
            throw new CustomException("Email already exists");
        }

        userRepository.save(user);
    }
}
