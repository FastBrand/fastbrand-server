package com.example.demo.admin.entity;

import com.example.demo.admin.dto.AdminDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    @CreatedDate
    private LocalDateTime created_at;

    public static Admin createAdmin(AdminDto dto) {
        return Admin.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
