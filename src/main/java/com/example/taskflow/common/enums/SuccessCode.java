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

    LOGIN_SUCCESS(HttpStatus.OK, "로그인이 완료되었습니다."),

//user
    PROFILE_SUCCESS(HttpStatus.OK, "사용자 정보를 조회했습니다."),

//task
    TASK_SUCCESS(HttpStatus.CREATED, "Task가 생성되었습니다."),
    TASK_LIST_SUCCESS(HttpStatus.CREATED, "Task 목록을 조회했습니다."),
    TASK_SINGLE_SUCCESS(HttpStatus.OK, "Task를 조회했습니다.")
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
