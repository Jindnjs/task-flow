package com.example.taskflow.domain.task.enums;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;

import java.util.Arrays;

public enum Status {
    TODO, IN_PROGRESS, DONE;

    public static Status of(String status) {
        return Arrays.stream(Status.values())
                .filter(r -> r.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new TaskFlowException(ErrorCode.STATUS_NOT_EXISTS));
    }
}
