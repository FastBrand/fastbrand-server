package com.example.demo.admin.jwt;

public interface JwtProperties {
    String SECRET = "auths"; // 랜덤한 값?
    int EXPIRATION_TIME = 10000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
