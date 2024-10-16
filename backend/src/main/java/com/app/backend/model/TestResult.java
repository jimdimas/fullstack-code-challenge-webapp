package com.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TestResult {
    @Id
    @SequenceGenerator(
            name = "testResultSequence",
            sequenceName = "testResultSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "testResultSequence",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    @ManyToOne
    private Test test;
    @ManyToOne
    private Student student;
    private Double result;
}
