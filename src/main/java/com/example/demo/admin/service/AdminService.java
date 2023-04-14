package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(AdminDto dto) {
        Admin admin = Admin.createAdmin(dto);
        String password = admin.getPassword();
        String enPassword = bCryptPasswordEncoder.encode(password);
        admin.setPassword(enPassword);
        admin.setRole("ROLE_MANAGER");
        adminRepository.save(admin);
    }

    public List<Admin> managers() {
        return adminRepository.findAll();
    }
}
