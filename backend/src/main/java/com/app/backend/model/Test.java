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
public class Test {
    @Id
    @SequenceGenerator(
            name="testSequence",
            sequenceName = "testSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "testSequence"
    )
    @JsonIgnore
    private Integer id;
    @Column(unique = true)
    private String title;
    @OneToMany
    private List<Question> questions;
    private Integer points;
}
