package com.example.taskflow.common.utils;

import com.example.taskflow.common.enums.BaseCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HttpResponseUtil {

    public static void throwError(
            HttpServletResponse response,
            BaseCode errorCode
    ) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String body = errorCode.getMessage();

        response.getWriter().write(body);
    }
}
