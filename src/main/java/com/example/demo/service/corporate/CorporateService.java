package com.example.demo.service.corporate;

import com.example.demo.dto.CorporateDto;
import java.util.List;

public interface CorporateService {
    List<CorporateDto> corporate();
    CorporateDto oneCorporate(Long id);
    CorporateDto create(Long mark_id, CorporateDto dto);
    CorporateDto update(Long id, CorporateDto dto);
    CorporateDto delete(Long id);
}
