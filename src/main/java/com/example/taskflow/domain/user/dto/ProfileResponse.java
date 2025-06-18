package com.example.taskflow.domain.user.dto;

import com.example.taskflow.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String role;
    private LocalDateTime createdAt;

    public static ProfileResponse toDto(User user) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.id = user.getId();
        profileResponse.username = user.getUsername();
        profileResponse.name = user.getName();
        profileResponse.email = user.getEmail();
        profileResponse.role = user.getRole().toString();
        profileResponse.createdAt = user.getCreatedAt();
        return profileResponse;
    }
}
