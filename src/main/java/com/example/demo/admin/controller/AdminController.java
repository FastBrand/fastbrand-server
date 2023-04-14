package com.example.demo.admin.controller;

import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.service.PrincipalDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin/managers")
    public List<Admin> managers(){
        return adminService.managers();
    }

    @PostMapping("/join")
    public String join(@RequestBody AdminDto dto) {
        adminService.join(dto);
        return "success";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}
