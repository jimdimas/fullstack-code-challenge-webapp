package com.app.backend.service;

import com.app.backend.exception.CustomException;
import com.app.backend.model.Solution;
import com.app.backend.model.Student;
import com.app.backend.model.User;
import com.app.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents(User user){
        if (!user.getRole().equals("ADMIN")){
            throw new CustomException("Unable to perform action");
        }

        return studentRepository.findAll();
    }
    public String updateStudentRanking(Solution solution){
        Student student = solution.getSolvedBy();
        student.setRanking(student.getRanking()+solution.getForProblem().getPoints());
        studentRepository.save(student);
        return "Student ranking updated";
    }
}
