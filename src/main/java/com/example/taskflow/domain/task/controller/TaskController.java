package com.example.taskflow.domain.task.controller;

import com.example.taskflow.common.annotation.Authen;
import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.dto.AuthUser;
import com.example.taskflow.common.dto.PageResponse;
import com.example.taskflow.common.enums.SuccessCode;
import com.example.taskflow.domain.task.dto.StatusUpdateDto;
import com.example.taskflow.domain.task.dto.TaskCreateRequest;
import com.example.taskflow.domain.task.dto.TaskResponse;
import com.example.taskflow.domain.task.enums.Status;
import com.example.taskflow.domain.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/tasks")
    public ResponseEntity<ApiResponse<TaskResponse>> create(
            @Valid @RequestBody TaskCreateRequest request,
            @Authen AuthUser authUser
    ){
        TaskResponse response = taskService.create(request, authUser.getUserId());
        return ApiResponse.success(SuccessCode.TASK_SUCCESS).body(response);
    }

    @GetMapping("/api/tasks")
    public ResponseEntity<ApiResponse<PageResponse<TaskResponse>>> getlist(
            @RequestParam Status status,
            Pageable pageable
    ) {
        Page<TaskResponse> tasks = taskService.getlist(status, pageable);
        return ApiResponse.success(SuccessCode.TASK_LIST_SUCCESS).body(new PageResponse<>(tasks));
    }

    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<ApiResponse<TaskResponse>> get(
            @PathVariable Long taskId
    ) {
        TaskResponse task = taskService.get(taskId);
        return ApiResponse.success(SuccessCode.TASK_SINGLE_SUCCESS).body(task);
    }

    @PatchMapping("/api/tasks/{taskId}/status")
    public ResponseEntity<ApiResponse<TaskResponse>> status(
            @PathVariable Long taskId,
            @Authen AuthUser authUser,
            @RequestBody StatusUpdateDto dto
    ){
        TaskResponse updated = taskService.status(taskId, authUser, dto);
        return ApiResponse.success(SuccessCode.TASK_UPDATE_SUCCESS).body(updated);
    }

    @DeleteMapping("/api/tasks/{taskId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long taskId,
            @Authen AuthUser authUser
    ){
        taskService.delete(taskId, authUser);
        return ApiResponse.success(SuccessCode.TASK_DELETE_SUCCESS).body();
    }
}
