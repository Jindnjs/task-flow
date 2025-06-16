package com.example.taskflow.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskFlowException.class)
    public ResponseEntity<String> handleTaskFlowExecption(TaskFlowException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(ex.getMessage());
    }
}
