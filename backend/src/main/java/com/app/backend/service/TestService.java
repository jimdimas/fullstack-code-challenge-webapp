package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Student;
import com.app.backend.model.Test;
import com.app.backend.model.TestResult;
import com.app.backend.model.User;
import com.app.backend.repository.QuestionRepository;
import com.app.backend.repository.TestRepository;
import com.app.backend.repository.TestResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    public List<Test> getAllTests(){
        return testRepository.findAll();
    }

    public Test getTestByTitle(String title){
        Optional<Test> testExists = testRepository.findByTitle(title);
        if (!testExists.isPresent()){
            throw new CustomException("Test with given title doesn't exist");
        }
        return testExists.get();
    }

    @Transactional
    public String postTest(User user,Test test){
        if (!user.getRole().equals("SUPERVISOR")){
            throw new CustomException("You do not have access to given resource");
        }
        Optional<Test> testExists = testRepository.findByTitle(test.getTitle());
        if (testExists.isPresent()){
            throw new CustomException("Test with given title already exists");
        }

        questionRepository.saveAll(test.getQuestions());
        testRepository.save(test);
        return "Test was uploaded succesfully";
    }
}
