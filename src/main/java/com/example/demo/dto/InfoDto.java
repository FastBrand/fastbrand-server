package com.example.demo.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InfoDto {
    private MarkDto mark;
    private PersonalDto personal;
    private CorporateDto corporate;
    private UserDto user;
    private List<ImageDto> images;
    private List<SealDto> seals;
}
