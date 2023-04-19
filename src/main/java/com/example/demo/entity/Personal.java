package com.example.demo.entity;

import com.example.demo.dto.PersonalDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Mark mark;
    @Column
    @Size(max=64)
    @NotNull
    private String name_kor;
    @Column
    @Size(max=64)
    @NotNull
    private String name_eng;
    @Column
    @Size(max=64)
    @NotNull
    private String ssn;
    @Column
    @Size(max=128)
    @NotNull
    @Email
    private String personalEmail;
    @Column
    @Size(max=64)
    @NotNull
    private String personalMobile;
    @Column
    @Size(max=64)
    private String personalPhone;
    @Column
    @Size(max=128)
    @NotNull
    private String address;
    @Column
    @Size(max=128)
    @NotNull
    private String detail;
    @Column
    @Size(max=64)
    @NotNull
    private String zipcode;
    @Column
    @Size(max=16)
    @NotNull
    private String agreement;

    public static Personal createPersonal(PersonalDto dto, Mark mark) {
        return Personal.builder()
                .id(dto.getId())
                .mark(mark)
                .name_kor(dto.getName_kor())
                .name_eng(dto.getName_eng())
                .ssn(dto.getSsn())
                .personalEmail(dto.getPersonalEmail())
                .personalMobile(dto.getPersonalMobile())
                .personalPhone(dto.getPersonalPhone())
                .address(dto.getAddress())
                .detail(dto.getDetail())
                .zipcode(dto.getZipcode())
                .agreement(dto.getAgreement())
                .build();
    }

    public void patch(PersonalDto personal) {
        if(personal.getName_kor() != null)
            this.name_kor = personal.getName_kor();
        if(personal.getName_eng() != null)
            this.name_eng = personal.getName_eng();
        if(personal.getSsn() != null)
            this.ssn = personal.getSsn();
        if(personal.getPersonalEmail() != null)
            this.personalEmail = personal.getPersonalEmail();
        if(personal.getPersonalMobile() != null)
            this.personalMobile = personal.getPersonalMobile();
        if(personal.getPersonalPhone() != null)
            this.personalPhone = personal.getPersonalPhone();
        if(personal.getAddress() != null)
            this.address = personal.getAddress();
        if(personal.getDetail() != null)
            this.detail = personal.getDetail();
        if(personal.getZipcode() != null)
            this.zipcode = personal.getZipcode();
        if(personal.getAddress() != null)
            this.agreement = personal.getAgreement();
    }
}
