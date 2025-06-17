package com.example.taskflow.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    //default
    SUCCESS(HttpStatus.OK, "success"),

//auth
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료되었습니다."),

    LOGIN_SUCCESS(HttpStatus.OK, "로그인이 완료되었습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
