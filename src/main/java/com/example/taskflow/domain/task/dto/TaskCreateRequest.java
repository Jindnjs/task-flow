package com.example.taskflow.domain.task.dto;

import com.example.taskflow.domain.task.enums.Priority;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskCreateRequest {
    private String title;
    private String description;
    private Priority priority;
    private Long assigneeId;
    private LocalDateTime dueDate;
}
