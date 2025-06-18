package com.example.taskflow.common.dto;

import com.example.taskflow.domain.user.enums.UserRole;
import lombok.Getter;

@Getter
public class AuthUser {
    private Long userId;
    private String email;
    private UserRole userRole;

    public AuthUser(Long userId, String email, UserRole userRole) {
        this.userId = userId;
        this.email = email;
        this.userRole = userRole;
    }
}
