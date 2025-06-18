package com.example.taskflow.common.filter;

import com.example.taskflow.common.enums.ErrorCode;
import com.example.taskflow.common.utils.HttpResponseUtil;
import com.example.taskflow.common.utils.JwtUtil;
import com.example.taskflow.domain.user.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
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

        //cors 통과
        if(httpRequest.getMethod().equals("OPTIONS")){
            chain.doFilter(request, response);
            return;
        }
        // 회원가입 로그인 관련 통과로직
        String requestURI = httpRequest.getRequestURI();

        if(requestURI.startsWith("/api/auth")){
            chain.doFilter(request, response);
            return;
        }

        // jwt토큰 유효성 검사 로직
        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken == null){
            HttpResponseUtil.throwError(httpResponse, ErrorCode.TOKEN_NOT_EXISTS);
            return;
        }

        String jwt = jwtUtil.substringToken(bearerToken);

        try {
            // JWT 유효성 검사와 claims 추출
            Claims claims = jwtUtil.extractClaims(jwt);

            UserRole.valueOf(claims.get("userRole", String.class));

            httpRequest.setAttribute("userId", Long.parseLong(claims.getSubject()));
            httpRequest.setAttribute("email", claims.get("email"));
            httpRequest.setAttribute("userRole", claims.get("userRole"));

            chain.doFilter(request, response);
        } catch (SecurityException | MalformedJwtException e) {
            HttpResponseUtil.throwError(httpResponse, ErrorCode.TOKEN_SIGNATURE_ERROR);
        } catch (ExpiredJwtException e) {
            HttpResponseUtil.throwError(httpResponse, ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            HttpResponseUtil.throwError(httpResponse, ErrorCode.TOKEN_INVALID);
        }


        // Todo: 어드민 관련 통과로직


    }
}
