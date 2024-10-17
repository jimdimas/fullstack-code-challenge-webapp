package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Student;
import com.app.backend.model.Test;
import com.app.backend.model.TestResult;
import com.app.backend.repository.TestRepository;
import com.app.backend.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;
    private final TestRepository testRepository;

    public List<TestResult> getStudentTestResults(String username){
        return testResultRepository.findByUsername(username);
    }
    public String postTestResult(String title, Student student, TestResult testResult) {
        Optional<Test> testExists = testRepository.findByTitle(title);
        if (!testExists.isPresent()){
            throw new CustomException("Test with given title doesn't exist");
        }
        if (testResult.getResult()<0.5){
            throw new CustomException("Test grade cannot be accepted");
        }

        Optional<TestResult> hasDoneTest = testResultRepository.findByTestTitleAndUsername(title,student.getUsername());
        if (hasDoneTest.isPresent()){
            TestResult savedResult = hasDoneTest.get();
            if (savedResult.getResult()<testResult.getResult()){
                savedResult.setResult(testResult.getResult());
                savedResult.setSolvedAt(new Date());
                testResultRepository.save(savedResult);
                return "Test result was updated succesfully";
            }
            return "Test result was not better than user best";
        }

        testResult.setTest(testExists.get());
        testResult.setStudent(student);
        testResult.setSolvedAt(new Date());
        testResultRepository.save(testResult);
        return "Test result was uploaded succesfully";
    }
}
