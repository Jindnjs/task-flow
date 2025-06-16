package com.example.taskflow.common.exception;

import com.example.taskflow.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class TaskFlowException extends RuntimeException {

    private ErrorCode errorCode;

    public TaskFlowException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
