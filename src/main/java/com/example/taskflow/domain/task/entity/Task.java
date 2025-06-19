package com.example.taskflow.domain.task.entity;

import com.example.taskflow.common.entity.BaseEntity;
import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;
import com.example.taskflow.domain.task.enums.Priority;
import com.example.taskflow.domain.task.enums.Status;
import com.example.taskflow.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dueDate;
    private LocalDateTime startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    public Task(String title, String description, Priority priority, LocalDateTime dueDate, User creator, User assignee) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.TODO;
        this.dueDate = dueDate;
        this.creator = creator;
        this.assignee = assignee;
    }
    public void updateStatus(Status status) {
        if (this.status.isChange(status)) {
            this.status = status;
        }
        else{
            throw new TaskFlowException(ErrorCode.STATUS_CANT_CHANGE);
        }
    }
}

