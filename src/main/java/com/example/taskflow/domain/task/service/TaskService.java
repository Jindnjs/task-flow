package com.example.taskflow.domain.task.service;

import com.example.taskflow.common.dto.AuthUser;
import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.exception.TaskFlowException;
import com.example.taskflow.domain.task.dto.StatusUpdateDto;
import com.example.taskflow.domain.task.dto.TaskCreateRequest;
import com.example.taskflow.domain.task.dto.TaskResponse;
import com.example.taskflow.domain.task.entity.Task;
import com.example.taskflow.domain.task.enums.Status;
import com.example.taskflow.domain.task.repository.TaskRepository;
import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.enums.UserRole;
import com.example.taskflow.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskResponse create(TaskCreateRequest request, Long userId) {

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
        return TaskResponse.of(saved);
    }

    public Page<TaskResponse> getlist(Status status, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByStatusAndIsDeletedIsFalse(status, pageable);
        return tasks.map(TaskResponse::of);
    }

    public TaskResponse get(Long taskId) {
        Task task = taskRepository.findByIdAndIsDeletedIsFalse(taskId)
                .orElseThrow(() -> new TaskFlowException(ErrorCode.TASK_NOT_EXISTS));
        return TaskResponse.of(task);
    }

    public TaskResponse status(Long taskId, AuthUser authUser, StatusUpdateDto dto) {
        Task task = taskRepository.findByIdAndIsDeletedIsFalse(taskId)
                .orElseThrow(() -> new TaskFlowException(ErrorCode.TASK_NOT_EXISTS));
        if (!isAuthorized(authUser, task.getAssignee().getId())){
            throw new TaskFlowException(ErrorCode.USER_UNAUTHORIZED);
        }
        task.updateStatus(Status.of(dto.getStatus()));
        Task updated = taskRepository.save(task);
        return TaskResponse.of(updated);
    }

    @Transactional
    public void delete(Long taskId, AuthUser authUser) {
        Task task = taskRepository.findByIdAndIsDeletedIsFalse(taskId)
                .orElseThrow(() -> new TaskFlowException(ErrorCode.TASK_NOT_EXISTS));
        if (!isAuthorized(authUser, task.getAssignee().getId())){
            throw new TaskFlowException(ErrorCode.USER_UNAUTHORIZED);
        }
        task.delete();
    }

    private boolean isAuthorized(AuthUser authUser, Long userId) {
        return authUser.getUserRole().equals(UserRole.ADMIN) || authUser.getUserId().equals(userId);
    }
}
