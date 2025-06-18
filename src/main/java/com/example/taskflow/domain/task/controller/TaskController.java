package com.example.taskflow.domain.task.controller;

import com.example.taskflow.common.annotation.Authen;
import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.dto.AuthUser;
import com.example.taskflow.common.enums.SuccessCode;
import com.example.taskflow.domain.task.dto.TaskCreateRequest;
import com.example.taskflow.domain.task.dto.TaskCreateResponse;
import com.example.taskflow.domain.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/tasks")
    public ResponseEntity<ApiResponse<TaskCreateResponse>> create(
            @Valid @RequestBody TaskCreateRequest request,
            @Authen AuthUser authUser
            //TODO: valid
    ){
        TaskCreateResponse response = taskService.create(request, authUser.getUserId());
        return ApiResponse.success(SuccessCode.TASK_SUCCESS).body(response);
    }

    //@GetMapping("/api/")
}
