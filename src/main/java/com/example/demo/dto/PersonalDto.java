package com.example.demo.dto;

import com.example.demo.entity.Personal;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PersonalDto {
    private Long id;
    private Long mark_id;
    private String name_kor;
    private String name_eng;
    private String ssn;
    private String personalEmail;
    private String personalMobile;
    private String personalPhone;
    private String address;
    private String detail;
    private String zipcode;
    private String agreement;


    public static PersonalDto createPersonalDto(Personal personal) {
        return PersonalDto.builder()
                .id(personal.getId())
                .mark_id(personal.getMark().getId())
                .name_kor(personal.getName_kor())
                .name_eng(personal.getName_eng())
                .ssn(personal.getSsn())
                .personalEmail(personal.getPersonalEmail())
                .personalMobile(personal.getPersonalMobile())
                .personalPhone(personal.getPersonalPhone())
                .address(personal.getAddress())
                .detail(personal.getDetail())
                .zipcode(personal.getZipcode())
                .agreement(personal.getAgreement())
                .build();
    }

}
