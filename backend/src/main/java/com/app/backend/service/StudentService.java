package com.app.backend.service;

import com.app.backend.model.Solution;
import com.app.backend.model.Student;
import com.app.backend.repository.SolutionRepository;
import com.app.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SolutionRepository solutionRepository;

    public String updateStudentRanking(Student student,Solution solution){
        student.setRanking(student.getRanking()+solution.getForProblem().getPoints());
        studentRepository.save(student);
        return "Student ranking updated";
    }
}
