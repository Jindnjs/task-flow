package com.example.taskflow.domain.user.controller;

import com.example.taskflow.common.annotation.Authen;
import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.dto.AuthUser;
import com.example.taskflow.common.enums.SuccessCode;
import com.example.taskflow.domain.user.dto.ProfileResponse;
import com.example.taskflow.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/users/me")
    public ResponseEntity<ApiResponse<ProfileResponse>> profile(
            @Authen AuthUser authUser
            ) {
        ProfileResponse profile = userService.profile(authUser.getUserId());
        return ApiResponse.success(SuccessCode.PROFILE_SUCCESS).body(profile);
    }
}
