package com.note.web.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestLogger {

    protected static void ReqLogger(HttpServletRequest request) {
        System.out.println("====================================================");
        // 요청 정보 출력
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote Address: " + request.getRemoteAddr());

        // 요청 헤더 출력
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ": " + request.getHeader(headerName));
        });

        // 파라미터 출력
        request.getParameterMap().forEach((key, value) -> {
            System.out.println(key + ": " + String.join(", ", value));
        });

        // 바디 출력
        try {
            BufferedReader reader = request.getReader();
            String line;
            StringBuilder body = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                body.append(line);
            }

            System.out.println("Request Body: " + body.toString());

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("====================================================");
    }

}
