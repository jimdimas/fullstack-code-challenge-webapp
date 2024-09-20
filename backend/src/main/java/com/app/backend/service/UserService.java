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
    public void createUser(User user){
        userRepository.save(user);
    }
}
