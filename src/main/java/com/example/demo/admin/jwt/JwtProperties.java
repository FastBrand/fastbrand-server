package com.example.demo.admin.jwt;

public interface JwtProperties {
    String SECRET = "d64g689s7gs3df1seyry87uy416ascvbn1z2v1s6v18b4y86un16i81o"; // 랜덤한 값
    int EXPIRATION_TIME = 300000; // 5분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
