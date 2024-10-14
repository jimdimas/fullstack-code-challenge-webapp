package com.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @SequenceGenerator(
            name="questionSequence",
            sequenceName = "questionSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "questionSequence"
    )
    @JsonIgnore
    private Integer id;
    private String content;
    @ManyToOne
    private Test test;
    @ElementCollection
    private List<String> answers;
    private Integer correctAnswer;
}
