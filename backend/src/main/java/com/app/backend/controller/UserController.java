package com.app.backend.controller;

import com.app.backend.model.User;
import com.app.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/v1/user"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "{username}")
    public User getUserByUsername(@PathVariable(name="username") String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping()
    public void createUser(
            @RequestAttribute(name="user") User requestedBy,
            @RequestBody User user){
        userService.createUser(requestedBy,user);
    }
}
