package com.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"test","student"})})
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
    private Date solvedAt;
}
