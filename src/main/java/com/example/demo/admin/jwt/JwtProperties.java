package com.example.demo.admin.jwt;

public interface JwtProperties {
    String SECRET = "d649g4v12seyry8we0vodfew66as8v1vcn3q6z3xun16"; // 랜덤한 값
    int EXPIRATION_TIME = 3600000; // 1시간
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
