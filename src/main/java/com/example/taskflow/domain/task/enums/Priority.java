package com.example.taskflow.domain.task.enums;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;

import java.util.Arrays;

public enum Priority {
    LOW,MEDIUM,HIGH;

    public static Priority of(String priority) {
        return Arrays.stream(Priority.values())
                .filter(r -> r.name().equalsIgnoreCase(priority))
                .findFirst()
                .orElseThrow(() -> new TaskFlowException(ErrorCode.PRIORITY_NOT_EXISTS));
    }
}
