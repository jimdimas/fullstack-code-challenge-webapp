package com.app.backend.controller;

import com.app.backend.model.Student;
import com.app.backend.model.Test;
import com.app.backend.model.TestResult;
import com.app.backend.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/test"})
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public List<Test> getAllTests(){
        return testService.getAllTests();
    }

    @GetMapping(path = "{title}")
    public Test getTestByTitle(@PathVariable(name = "title") String title){
        return testService.getTestByTitle(title);
    }

    @PostMapping
    public String postTest(@RequestBody Test test){
        return testService.postTest(test);
    }
}
