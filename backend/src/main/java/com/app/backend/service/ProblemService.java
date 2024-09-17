package com.app.backend.service;

import com.app.backend.model.Problem;
import com.app.backend.model.Supervisor;
import com.app.backend.repository.ProblemRepository;
import com.app.backend.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final SupervisorRepository supervisorRepository;

    public List<Problem> getAllProblems(){
        return problemRepository.findAll();
    }

    public Problem getByProblemId(UUID problemId) {
        Optional<Problem> problemExists = problemRepository.findByProblemId(problemId);
        return problemExists.orElseGet(Problem::new);
    }

    public List<Problem> getBySupervisor(String username){
        return problemRepository.findBySupervisor(username);
    }

    public List<Problem> getByDifficulty(String difficulty){
        return problemRepository.findByDifficulty(difficulty);
    }

    public String createProblem(Problem problem){
        System.out.println(problem.getUploadedBy().getUsername());
        Optional<Supervisor> supervisorExists = supervisorRepository.
                findByUsername(problem.getUploadedBy().getUsername());
        if (supervisorExists.isEmpty()){
            return "Supervisor doesnt exist";
        }
        problem.setProblemId(UUID.randomUUID());
        problem.setUploadedBy(supervisorExists.get());
        problemRepository.save(problem);
        return "Problem Uploaded";
    }
}
