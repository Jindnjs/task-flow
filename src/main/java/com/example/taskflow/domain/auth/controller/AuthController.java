package com.example.taskflow.domain.auth.controller;

import com.example.taskflow.domain.auth.dto.request.SignupRequest;
import com.example.taskflow.domain.auth.dto.response.SignupResponse;
import com.example.taskflow.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/auth/signup")
    public SignupResponse signup(@RequestBody SignupRequest signupRequest){
        return authService.signup(signupRequest);
    }
}
