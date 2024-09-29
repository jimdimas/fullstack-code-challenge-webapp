package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Supervisor;
import com.app.backend.model.User;
import com.app.backend.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupervisorService {
    private final SupervisorRepository supervisorRepository;

    public List<Supervisor> getAllSupervisor(User user){
        if (!user.getRole().equals("ADMIN")){
            throw new CustomException("Unable to perform action");
        }

        return supervisorRepository.findAll();
    }
}
