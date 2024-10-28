package com.app.backend.controller;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Problem;
import com.app.backend.model.User;
import com.app.backend.service.JsonBody;
import com.app.backend.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping({"/api/v1/problem"})
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public List<Problem> getAllProblems(){
        return problemService.getAllProblems();
    }

    @GetMapping(path="{problemId}")
    public Problem getProblemById(@PathVariable UUID problemId) throws CustomException {
        return problemService.getByProblemId(problemId);
    }

    @GetMapping("/suggested")
    public Problem getSuggestedProblem(@RequestAttribute(name="user") User user){
        return problemService.getSuggestedProblems(user);
    }

    @PostMapping
    public String postProblem(
            @RequestAttribute(name="user") User user,
            @RequestBody Problem problem) throws CustomException {
        return problemService.createProblem(user,problem);
    }

    @PutMapping
    public ResponseEntity<JsonBody> editProblem(
            @RequestAttribute(name="user") User user,
            @RequestBody Problem problem
    ){
        return problemService.editProblem(user,problem);
    }

    @DeleteMapping(path="{problemId}")
    public ResponseEntity<JsonBody> deleteProblem(
            @RequestAttribute(name="user") User user,
            @PathVariable(name="problemId") UUID problemId
    ){
        return problemService.deleteProblem(user,problemId);
    }
}
