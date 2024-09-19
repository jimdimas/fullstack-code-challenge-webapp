package com.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Solution {

    @Id
    @SequenceGenerator(
            name = "solution_sequence",
            sequenceName = "solution_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "solution_sequence"
    )
    @JsonIgnore
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Student solvedBy;
    private String content;
    private Date solvedAt;
    private Integer rating;
    @ManyToOne(fetch = FetchType.EAGER)
    private Problem forProblem;
}