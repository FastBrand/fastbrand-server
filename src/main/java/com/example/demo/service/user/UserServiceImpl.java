package com.example.demo.service.user;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Mark;
import com.example.demo.entity.User;
import com.example.demo.repository.MarkRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MarkRepository markRepository;

    @Override
    public List<UserDto> user() {
        return userRepository.findAll().stream()
                .map(UserDto::createUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto oneUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        return UserDto.createUserDto(user);
    }

    @Override
    @Transactional
    public UserDto create(Long mid, UserDto dto) {
        Mark mark = markRepository.findById(mid)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        User user = User.createUser(dto, mark);
        User created = userRepository.save(user);
        return UserDto.createUserDto(created);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto dto) {
        User target = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        target.patch(dto);
        User updated = userRepository.save(target);
        return UserDto.createUserDto(updated);
    }

    @Override
    @Transactional
    public UserDto delete(Long id) {
        User target = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        userRepository.delete(target);
        return UserDto.createUserDto(target);
    }
}
