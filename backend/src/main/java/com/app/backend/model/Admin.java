package com.app.backend.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class Admin extends User{
    private String license;
    private Date createdAt;
}
