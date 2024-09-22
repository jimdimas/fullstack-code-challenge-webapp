package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Problem;
import com.app.backend.model.Solution;
import com.app.backend.model.Student;
import com.app.backend.model.User;
import com.app.backend.repository.ProblemRepository;
import com.app.backend.repository.SolutionRepository;
import com.app.backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
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
    private final StudentService studentService;

    public List<Solution> getProblemSolutions(UUID problemId){
        return solutionRepository.findByProblem(problemId);
    }

    public List<Solution> getStudentSolutions(String username){
        return solutionRepository.findByStudent(username);
    }

    public String postSolution(
            User user,
            UUID problemId,
            Solution solution) throws CustomException {

        if (!user.getRole().equals("STUDENT")){
            throw new CustomException("Only students are allowed to post solutions to problems");
        }
        Optional<Problem> problemExists = problemRepository.findByProblemId(problemId);
        if (problemExists.isEmpty()){
            throw new CustomException("Problem doesnt exist");
        }
        Optional<Student> studentExists = studentRepository.findByUsername(user.getUsername());
        if (studentExists.isEmpty()){
            throw new CustomException("Something went wrong,try again");
        }
        solution.setSolutionId(UUID.randomUUID());
        solution.setSolvedAt(new Date());
        solution.setForProblem(problemExists.get());
        solution.setSolvedBy(studentExists.get());
        solutionRepository.save(solution);
        return "solution uploaded succesfully";
    }

    public String gradeSolution(User gradedBy,UUID solutionId){
        if (!gradedBy.getRole().equals("SUPERVISOR")) throw new CustomException("Unable to perform action");

        Optional<Solution> solutionExists = solutionRepository.findBySolutionId(solutionId);
        if (solutionExists.isEmpty()) throw new CustomException("Solution with id "+solutionId.toString()+" doesnt exist");

        Solution solution = solutionExists.get();
        solution.setAccepted(true);
        solutionRepository.save(solution);
        studentService.updateStudentRanking(solution.getSolvedBy(),solution);
        return "Solution has been graded";
    }
}
