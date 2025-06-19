package com.example.taskflow.domain.task.dto;

import com.example.taskflow.domain.task.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskCreateRequest {
    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    @Size(min = 1, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "우선순위가 지정되지 않았습니다.")
    private Priority priority;

    @NotNull(message = "담당자가 지정되지 않았습니다.")
    private Long assigneeId;

    private LocalDateTime dueDate;
}
