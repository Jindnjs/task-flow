package com.example.taskflow.common.dto;

import com.example.taskflow.common.enums.BaseCode;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({ "success", "message", "data", "timestamp" })
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static Builder<?> success(BaseCode baseCode) {
        return new Builder<>(baseCode, true);
    }
    public static Builder<?> error(BaseCode baseCode) {
        return new Builder<>(baseCode, false);
    }

    public static class Builder<T> {
        private BaseCode baseCode;
        private boolean success;

        private Builder(BaseCode baseCode, boolean success) {
            this.baseCode = baseCode;
            this.success = success;
        }

        public ResponseEntity<ApiResponse<Void>> body(){
            return ResponseEntity
                    .status(baseCode.getHttpStatus())
                    .body(new ApiResponse<>(success, baseCode.getMessage(), null));
        }

        public <T> ResponseEntity<ApiResponse<T>> body(T body){
            return ResponseEntity
                    .status(baseCode.getHttpStatus())
                    .body(new ApiResponse<>(success, baseCode.getMessage(), body));
        }
    }
}