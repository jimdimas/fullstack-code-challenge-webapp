package com.app.backend.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    public CustomException(String _message){
        super(_message);
        this.message=_message;
    }

    private String message;
}
