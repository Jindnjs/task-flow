package com.example.taskflow.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements BaseCode {

//user
    //role
    USER_ROLE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "유효하지 않은 userRole 입니다."),

//auth
    //signup
    USER_NAME_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자명 입니다."),
    USER_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),
    //signin
    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 사용자명 또는 비밀번호입니다."),

// jwt
    //token
    TOKEN_NOT_EXISTS(HttpStatus.BAD_REQUEST, "JWT 토큰이 필요합니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "잘못된 토큰 형식 입니다."),
    TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED, "유효하지 않는 JWT 서명입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 JWT token 입니다."),
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "<UNK> <UNK> <UNK> <UNK>."),

    ;



    private final HttpStatus httpStatus;
    private final String message;
}
