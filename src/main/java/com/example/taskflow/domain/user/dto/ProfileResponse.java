package com.example.taskflow.domain.user.dto;

import com.example.taskflow.domain.user.entity.User;
import com.example.taskflow.domain.user.enums.UserRole;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
public class ProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private UserRole role;
    private LocalDateTime createdAt;

    public static ProfileResponse toDto(User user) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.id = user.getId();
        profileResponse.username = user.getUsername();
        profileResponse.name = user.getName();
        profileResponse.email = user.getEmail();
        profileResponse.role = user.getRole();
        profileResponse.createdAt = user.getCreatedAt();
        return profileResponse;
    }
    @QueryProjection
    public ProfileResponse(Long id, String username, String email, String name, UserRole role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
    }
}
