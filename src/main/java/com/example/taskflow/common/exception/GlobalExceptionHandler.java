package com.example.taskflow.common.exception;

import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskFlowException.class)
    public ResponseEntity<ApiResponse<Void>> handleTaskFlowExecption(TaskFlowException ex) {
        return ApiResponse.error(ex.getErrorCode()).body();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return ApiResponse.error(ErrorCode.VALIDATION_ERROR).body(errorMap);
    }
}
