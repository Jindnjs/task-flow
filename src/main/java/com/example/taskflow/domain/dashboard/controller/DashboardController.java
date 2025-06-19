package com.example.taskflow.domain.dashboard.controller;

import com.example.taskflow.common.annotation.Authen;
import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.dto.AuthUser;
import com.example.taskflow.common.enums.SuccessCode;
import com.example.taskflow.domain.dashboard.dto.DashboardResponse;
import com.example.taskflow.domain.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/api/dashboard/stats")
    public ResponseEntity<ApiResponse<DashboardResponse>> stats(
            @Authen AuthUser authUser
            ) {
        DashboardResponse stats = dashboardService.stats(authUser.getUserId());
        return ApiResponse.success(SuccessCode.SUCCESS).body(stats);
    }
}
