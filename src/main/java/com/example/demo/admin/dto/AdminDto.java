package com.example.demo.admin.dto;

import com.example.demo.admin.entity.Admin;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AdminDto {
    private String username;
    private String password;

    public static AdminDto createAdminDto(Admin admin) {
        return AdminDto.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .build();
    }
}
