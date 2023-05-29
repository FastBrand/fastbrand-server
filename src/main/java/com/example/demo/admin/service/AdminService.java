package com.example.demo.admin.service;

import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createAdmin(AdminDto dto) {
        Admin admin = Admin.createAdmin(dto);
        String password = admin.getPassword();
        String enPassword = bCryptPasswordEncoder.encode(password);
        admin.setPassword(enPassword);
        admin.setRole("ROLE_MANAGER");
        adminRepository.save(admin);
    }

    @Transactional
    public AdminDto updateAdmin(Long id, AdminDto dto) {
        Admin target = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        target.setUsername(dto.getUsername());
        target.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        Admin updated = adminRepository.save(target);
        return AdminDto.createAdminDto(updated);
    }

    @Transactional
    public AdminDto deleteAdmin(Long id) {
        Admin target = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        adminRepository.delete(target);
        return AdminDto.createAdminDto(target);
    }

    public List<Admin> managers() {
        return adminRepository.findAll();
    }
}
