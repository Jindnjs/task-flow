package com.example.taskflow.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //user

    //signup
    USER_NAME_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자명 입니다."),
    USER_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),
    //signin
    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 사용자명 또는 비밀번호입니다."),
    //role
    USER_ROLE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "유효하지 않은 userRole 입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
