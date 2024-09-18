package com.app.backend.service;

import com.app.backend.model.Problem;
import com.app.backend.model.Solution;
import com.app.backend.model.Student;
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

    public String postSolution(UUID problemId,Solution solution){
        Optional<Problem> problemExists = problemRepository.findByProblemId(problemId);
        if (problemExists.isEmpty()){
            return "Problem doesnt exist";
        }
        Optional<Student> studentExists = studentRepository.findByUsername(solution.getSolvedBy().getUsername());
        if (studentExists.isEmpty()){
            return "Student doesnt exist";
        }
        solution.setSolvedAt(new Date());
        solution.setForProblem(problemExists.get());
        solution.setSolvedBy(studentExists.get());
        solutionRepository.save(solution);
        return "Solution uploaded succesfully";
    }
}
