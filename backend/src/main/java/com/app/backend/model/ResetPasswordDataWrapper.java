package com.app.backend.model;

import lombok.Data;

@Data
public class ResetPasswordDataWrapper {
    private String token;
    private String password;
}
