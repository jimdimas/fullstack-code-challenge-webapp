package com.app.backend.controller;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Solution;
import com.app.backend.model.User;
import com.app.backend.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/"})
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService solutionService;

    @GetMapping(path = "/problem/{problemId}/solutions")
    public List<Solution> getProblemSolutions(
            @RequestAttribute(name="user") User user,
            @PathVariable(name="problemId") UUID problemId){
        return solutionService.getProblemSolutions(user,problemId);
    }

    @GetMapping(path="/student/{username}/solutions")
    public List<Solution> getStudentSolutions(@PathVariable(name="username") String username){
        return solutionService.getStudentSolutions(username);
    }

    @PostMapping(path="/problem/{problemId}/solutions")
    public String postProblemSolution(
            @RequestAttribute(name="user") User user,
            @PathVariable(name="problemId") UUID problemId,
            @RequestBody Solution solution) throws CustomException {
        return solutionService.postSolution(user,problemId,solution);
    }

    @PostMapping(path="/solution/{solutionId}")
    public String reviewSolution(
            @RequestAttribute(name="user") User user,
            @PathVariable UUID solutionId,
            @RequestParam(name="result") Boolean result){
        return solutionService.reviewSolution(user,solutionId,result);
    }
}
