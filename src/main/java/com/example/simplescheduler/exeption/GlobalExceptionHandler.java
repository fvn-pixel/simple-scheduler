package com.example.simplescheduler.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> handleTaskException(TaskException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
