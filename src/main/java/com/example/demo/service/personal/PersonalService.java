package com.example.demo.service.personal;

import com.example.demo.dto.CorporateDto;
import com.example.demo.dto.PersonalDto;
import java.util.List;

public interface PersonalService {
    List<PersonalDto> personal();
    PersonalDto onePersonal(Long id);
    PersonalDto create(Long mid, PersonalDto dto);
    PersonalDto update(Long id, PersonalDto dto);
    PersonalDto delete(Long id);
}
