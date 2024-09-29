package com.app.backend.controller;

import com.app.backend.model.Supervisor;
import com.app.backend.model.User;
import com.app.backend.service.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/supervisor"})
@RequiredArgsConstructor
public class SupervisorController {
    private final SupervisorService supervisorService;

    @GetMapping
    public List<Supervisor> getAllSupervisors(@RequestAttribute(name="user") User user){
        return supervisorService.getAllSupervisor(user);
    }
}
