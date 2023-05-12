package com.example.demo.service.info;

import com.example.demo.dto.CorporateDto;
import com.example.demo.dto.InfoDto;

import java.util.List;

public interface InfoService {
    List<InfoDto> info();
    InfoDto createCorp(InfoDto dto);
    InfoDto createPer(InfoDto dto);
    InfoDto update(InfoDto dto);
    InfoDto inf(Long id);
}
