package com.example.demo.entity;

import com.example.demo.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Mark mark;
    @Column
    @Size(max=64)
    @NotNull
    private String name;
    @Column
    @Size(max=128)
    @NotNull
    @Email
    private String email;
    @Column
    @Size(max=64)
    @NotNull
    private String mobile;
    @Column
    @Size(max=64)
    private String phone;
    @Column
    @Size(max=64)
    @NotNull
    private String acc_num;
    @Column
    @Size(max=64)
    private String price;
    @Column
    @CreatedDate
    private LocalDateTime created_at;

    public void patch(UserDto user) {
        if(user.getName() != null)
            this.name = user.getName();
        if(user.getEmail() != null)
            this.email = user.getEmail();
        if(user.getMobile() != null)
            this.mobile = user.getMobile();
        if(user.getPhone() != null)
            this.phone = user.getPhone();
        if(user.getPrice() != null)
            this.price = user.getPrice();
        if(user.getAcc_num() != null)
            this.acc_num = user.getAcc_num();
    }

    public static User createUser(UserDto dto, Mark mark) {
        return User.builder()
                .id(dto.getId())
                .mark(mark)
                .name(dto.getName())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .phone(dto.getPhone())
                .acc_num(dto.getAcc_num())
                .price(dto.getPrice())
                .created_at(dto.getCreated_at())
                .build();
    }
}
