package com.example.demo.service.info;

import com.example.demo.dto.CorporateDto;
import com.example.demo.dto.InfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InfoService {
    List<InfoDto> info();
    InfoDto createCorp(InfoDto dto, MultipartFile[] images, String imageType, MultipartFile[] seals, String sealType);
    InfoDto createPer(InfoDto dto, MultipartFile[] images, String imageType);
    InfoDto update(InfoDto dto);
    InfoDto _info(Long id);
}
