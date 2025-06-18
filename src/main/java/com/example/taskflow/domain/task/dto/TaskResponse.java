package com.example.taskflow.domain.task.dto;

import com.example.taskflow.domain.task.entity.Task;
import com.example.taskflow.domain.task.enums.Priority;
import com.example.taskflow.domain.task.enums.Status;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Priority priority;
    private Status status;
    private Long assigneeId;
    private Assignee assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TaskResponse of(Task task) {
        TaskResponse response = new TaskResponse();
        response.id = task.getId();
        response.title = task.getTitle();
        response.description = task.getDescription();
        response.dueDate = task.getDueDate();
        response.priority = task.getPriority();
        response.status = task.getStatus();
        response.assigneeId = task.getAssignee().getId();
        response.assignee = Assignee.of(task.getAssignee());
        response.createdAt = task.getCreatedAt();
        response.updatedAt = task.getModifiedAt();
        return response;
    }
}
