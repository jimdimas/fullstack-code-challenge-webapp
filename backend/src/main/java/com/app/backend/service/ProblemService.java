package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.*;
import com.app.backend.repository.ProblemRepository;
import com.app.backend.repository.SolutionRepository;
import com.app.backend.repository.StudentRepository;
import com.app.backend.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final SupervisorRepository supervisorRepository;
    private final StudentRepository studentRepository;
    private final SolutionRepository solutionRepository;

    public List<Problem> getAllProblems(){
        return problemRepository.findAll().stream().sorted(Comparator.comparing(Problem::getPoints)).toList();
    }

    public Problem getSuggestedProblems(User user){
        Optional<Student> studentExists = studentRepository.findByUsername(user.getUsername());

        if (studentExists.isEmpty()){
            throw new CustomException("Cannot perform action");
        }
        Student student = studentExists.get();

        List<Problem> problems = problemRepository.findAll();
        List<Problem> suggestedProblems = new ArrayList<>();

        for (int i=0; i<problems.size(); i++){
            Optional<Solution> hasSolved = solutionRepository.findIfUserSolved(
                    problems.get(i).getProblemId(),
                    user.getUsername());
            if (hasSolved.isEmpty()){
                suggestedProblems.add(problems.get(i));
            }
        }

        HashMap<Problem,Double> problemSuggestionProb = new HashMap<>();
        for (int i=0; i<suggestedProblems.size(); i++){
            Problem tempProblem = suggestedProblems.get(i);

            Integer successes = tempProblem.
                    getSolutions().
                    stream().
                    filter(Solution::getAccepted).
                    toList().
                    size();
            problemSuggestionProb.put(tempProblem,
                    (1.0*(tempProblem.getPoints()/((successes+1))*(tempProblem.getSolutions().size()-successes+1))));
        }

        HashMap<Problem,Double> sortedProblems = problemSuggestionProb.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<Problem> result = new ArrayList<>();

        for (Map.Entry<Problem, Double> en :
                sortedProblems.entrySet()) {
            result.add(en.getKey());
        }

        List<Solution> studentSolutions = solutionRepository.findByStudent(student.getUsername());

        Double scaleStudentBySuccess = (studentSolutions.
                stream().
                filter(Solution::getAccepted).
                toList().
                size()*1.0 / (studentSolutions.size() + 1) - 0.5);

        Integer getSuggestedProblem = (int) (
                ((student.getRanking()+student.getRanking()*scaleStudentBySuccess)/2500)*suggestedProblems.size());
        return suggestedProblems.get(getSuggestedProblem);
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
