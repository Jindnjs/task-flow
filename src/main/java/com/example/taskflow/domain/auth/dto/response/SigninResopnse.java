package com.example.taskflow.domain.auth.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SigninResopnse {
    private String token;

    public SigninResopnse(String token) {
        this.token = token;
    }
}
