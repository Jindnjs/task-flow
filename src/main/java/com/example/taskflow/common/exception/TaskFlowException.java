package com.example.taskflow.common.exception;

import com.example.taskflow.common.enums.BaseCode;
import lombok.Getter;

@Getter
public class TaskFlowException extends RuntimeException {

    private BaseCode errorCode;

    public TaskFlowException(BaseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
