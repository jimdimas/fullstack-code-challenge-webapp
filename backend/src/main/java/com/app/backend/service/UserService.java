package com.app.backend.service;

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
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
