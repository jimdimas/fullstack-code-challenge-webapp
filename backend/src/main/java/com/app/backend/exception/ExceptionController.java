package com.app.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<Object> customExceptionHandler(
            RuntimeException exception,
            WebRequest request
    ){
        CustomError error = new CustomError(new Date(),exception.getMessage());
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"An exception was thrown.",exception);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> nativeExceptionHandler(
            RuntimeException exception,
            WebRequest request
    ){
        CustomError error = new CustomError(new Date(),exception.getMessage());
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE,"An exception was thrown.",exception);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> baseExceptionHandler(
            RuntimeException exception,
            WebRequest request,
            String message,
            HttpStatusCode statusCode
    ){
        Map<String,String> responseBody = new HashMap<String,String>();
        responseBody.put("message", message);
        return handleExceptionInternal(
                exception,
                responseBody,
                new HttpHeaders(),
                statusCode,
                request
        );
    }
}
