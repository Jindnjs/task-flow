package com.example.taskflow.domain.auth.dto.response;

import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SignupResponse {
    private Long id;
    private String username;
    private String email;
    private UserRole role;
    private String name;
    private LocalDateTime createdAt;

    public SignupResponse(Long id, String username, String email, UserRole role, String name, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static SignupResponse of(User user) {
        return new SignupResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), user.getName(), user.getCreatedAt());
    }
}

