package com.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    @Id
    @SequenceGenerator(
            name = "problem_sequence",
            sequenceName = "problem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "problem_sequence"
    )
    @JsonIgnore
    private Integer id;
    private UUID problemId;
    @ManyToOne
    private Supervisor uploadedBy;
    private String difficulty;
    private String question;
    private Date expiresAt;
    private Integer points;
    private Integer untilRanking;
}
