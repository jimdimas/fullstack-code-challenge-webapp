package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Problem;
import com.app.backend.model.Supervisor;
import com.app.backend.model.User;
import com.app.backend.repository.ProblemRepository;
import com.app.backend.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;

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

    public Problem getByProblemId(UUID problemId) throws CustomException {
        Optional<Problem> problemExists = problemRepository.findByProblemId(problemId);
        if (problemExists.isEmpty()){
            throw new CustomException("No problem with given id: "+problemId.toString()+" exists.");
        }

        return problemExists.get();
    }

    public List<Problem> getBySupervisor(String username){
        return problemRepository.findBySupervisor(username);
    }

    public List<Problem> getByDifficulty(String difficulty){
        return problemRepository.findByDifficulty(difficulty);
    }

    public String createProblem(User user, Problem problem) throws CustomException {
        if (!user.getRole().equals("SUPERVISOR")){
            throw new CustomException("Only supervisors are allowed to post new problems");
        }
        Optional<Supervisor> supervisorExists = supervisorRepository.
                findByUsername(user.getUsername());
        if (supervisorExists.isEmpty()){
            throw new CustomException("Supervisor doesnt exist");
        }
        problem.setProblemId(UUID.randomUUID());
        problem.setUploadedBy(supervisorExists.get());
        problem.setUntilRanking(10*problem.getPoints());
        problemRepository.save(problem);
        return "Problem Uploaded";
    }
}
