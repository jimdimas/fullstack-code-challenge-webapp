package com.app.backend.controller;

import com.app.backend.model.Problem;
import com.app.backend.model.User;
import com.app.backend.service.ProblemService;
import lombok.RequiredArgsConstructor;
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
    public Problem getProblemById(@PathVariable UUID problemId){
        return problemService.getByProblemId(problemId);
    }

    @PostMapping
    public String postProblem(
            @RequestAttribute(name="user") User user,
            @RequestBody Problem problem){
        return problemService.createProblem(user,problem);
    }
}
