package com.example.taskflow.domain.auth.controller;

import com.example.taskflow.common.dto.ApiResponse;
import com.example.taskflow.common.enums.SuccessCode;
import com.example.taskflow.domain.auth.dto.request.SigninRequest;
import com.example.taskflow.domain.auth.dto.request.SignupRequest;
import com.example.taskflow.domain.auth.dto.response.SigninResopnse;
import com.example.taskflow.domain.auth.dto.response.SignupResponse;
import com.example.taskflow.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest signupRequest){
        SignupResponse response = authService.signup(signupRequest);
        return ApiResponse.success(SuccessCode.SIGN_UP_SUCCESS).body(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<SigninResopnse>> signin(@RequestBody SigninRequest signinRequest){
        SigninResopnse response = authService.signin(signinRequest);
        return ApiResponse.success(SuccessCode.LOGIN_SUCCESS).body(response);
    }
}
