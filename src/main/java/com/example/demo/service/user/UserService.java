package com.example.demo.service.user;

import com.example.demo.dto.PersonalDto;
import com.example.demo.dto.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> user();
    UserDto oneUser(Long id);
    UserDto create(Long mid, UserDto dto);
    UserDto update(Long id, UserDto dto);
    UserDto delete(Long id);
}
