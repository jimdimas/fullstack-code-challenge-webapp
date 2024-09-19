package com.app.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomError {
    private Date timestamp;
    private String message;
}
