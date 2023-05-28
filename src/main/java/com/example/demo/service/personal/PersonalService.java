package com.example.demo.service.personal;

import com.example.demo.dto.PersonalDto;
import java.util.List;

public interface PersonalService {
    List<PersonalDto> personal();
    PersonalDto onePersonal(Long id);
    PersonalDto create(Long mark_id, PersonalDto dto);
    PersonalDto update(Long id, PersonalDto dto);
    PersonalDto delete(Long id);
}
