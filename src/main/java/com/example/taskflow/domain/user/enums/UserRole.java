package com.example.taskflow.domain.user.enums;

import java.util.Arrays;

public enum UserRole {
    ADMIN, USER;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("유효하지 않은 UerRole"));
        //Todo : 오류 바꾸기
    }
}
