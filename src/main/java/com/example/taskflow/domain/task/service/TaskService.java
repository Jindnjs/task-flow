package com.example.taskflow.domain.task.service;

import com.example.taskflow.domain.task.dto.TaskCreateRequest;
import com.example.taskflow.domain.task.dto.TaskCreateResponse;
import com.example.taskflow.domain.task.entity.Task;
import com.example.taskflow.domain.task.repository.TaskRepository;
import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskCreateResponse create(TaskCreateRequest request, Long userId) {

        User creator = userService.getExistUserById(userId);
        User assignee = userService.getExistUserById(request.getAssigneeId());

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getDueDate(),
                creator,
                assignee
        );

        Task saved = taskRepository.save(task);
        return TaskCreateResponse.of(saved);
    }
}
