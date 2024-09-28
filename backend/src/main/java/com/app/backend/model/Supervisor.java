package com.app.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@Table(name="supervisor")
@AllArgsConstructor
@NoArgsConstructor
public class Supervisor extends User{

    private String organization;
    private String expertise;
}
