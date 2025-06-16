package com.example.taskflow.common.jwt;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.utils.HttpResponseUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 회원가입 로그인 관련 통과로직
        String requestURI = httpRequest.getRequestURI();

        if(requestURI.startsWith("/auth")){
            chain.doFilter(request, response);
            return;
        }

        // jwt토큰 유효성 검사 로직
        String bearerToken = httpRequest.getHeader("Authorization");

        if (bearerToken == null){
            HttpResponseUtil.throwError(httpResponse, ErrorCode.TOKEN_NOT_EXISTS);
            return;
        }

        chain.doFilter(request, response);


        // Todo: 어드민 관련 통과로직


    }
}
