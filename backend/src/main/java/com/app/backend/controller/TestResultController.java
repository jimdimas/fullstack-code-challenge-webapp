package com.app.backend.controller;

import com.app.backend.model.Student;
import com.app.backend.model.TestResult;
import com.app.backend.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/result"})
@RequiredArgsConstructor
public class TestResultController {
    private final TestResultService testResultService;

    @GetMapping
    public List<TestResult> getStudentsTestResults(@RequestParam(name="username") String username){
        return testResultService.getStudentTestResults(username);
    }

    @PostMapping(path="{title}")
    public String postTestResult(
            @PathVariable(name="title") String title,
            @RequestAttribute(name = "user") Student student,
            @RequestBody TestResult testResult){
        return testResultService.postTestResult(title,student,testResult);
    }
}
