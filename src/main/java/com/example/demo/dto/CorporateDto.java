package com.example.demo.dto;

import com.example.demo.entity.Corporate;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CorporateDto {
    private Long id;
    private Long mark_id;
    private String name_kor;
    private String name_eng;
    private String brn;
    private String crn;
    private String corporateName;
    private String ssn;
    private String corporateMobile;
    private String corporatePhone;
    private String corporateEmail;
    private String address;
    private String detail;
    private String zipcode;
    private String agreement;

    public static CorporateDto createCorporateDto(Corporate corporate) {
        return CorporateDto.builder()
                .id(corporate.getId())
                .mark_id(corporate.getMark().getId())
                .name_kor(corporate.getName_kor())
                .name_eng(corporate.getName_eng())
                .brn(corporate.getBrn())
                .crn(corporate.getCrn())
                .corporateName(corporate.getCorporateName())
                .ssn(corporate.getSsn())
                .corporateMobile(corporate.getCorporateMobile())
                .corporatePhone(corporate.getCorporatePhone())
                .corporateEmail(corporate.getCorporateEmail())
                .address(corporate.getAddress())
                .detail(corporate.getDetail())
                .zipcode(corporate.getZipcode())
                .agreement(corporate.getAgreement())
                .build();
    }


}
