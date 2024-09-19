package com.app.backend.service;

import com.app.backend.model.Problem;
import com.app.backend.model.Solution;
import com.app.backend.model.Student;
import com.app.backend.model.User;
import com.app.backend.repository.ProblemRepository;
import com.app.backend.repository.SolutionRepository;
import com.app.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final ProblemRepository problemRepository;
    private final StudentRepository studentRepository;

    public List<Solution> getProblemSolutions(UUID problemId){
        return solutionRepository.findByProblem(problemId);
    }

    public List<Solution> getStudentSolutions(String username){
        return solutionRepository.findByStudent(username);
    }

    public String postSolution(
            User user,
            UUID problemId,
            Solution solution){

        if (!user.getRole().equals("STUDENT")){
            return "Only students are allowed to post solutions to problems";
        }
        Optional<Problem> problemExists = problemRepository.findByProblemId(problemId);
        if (problemExists.isEmpty()){
            return "Problem doesnt exist";
        }
        Optional<Student> studentExists = studentRepository.findByUsername(user.getUsername());
        if (studentExists.isEmpty()){
            return "Something went wrong,try again";
        }
        solution.setSolvedAt(new Date());
        solution.setForProblem(problemExists.get());
        solution.setSolvedBy(studentExists.get());
        solutionRepository.save(solution);
        return "Solution uploaded succesfully";
    }
}
