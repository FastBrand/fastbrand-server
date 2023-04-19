package com.example.demo.admin.controller;

import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.service.AnalyticsService;
import com.example.demo.admin.service.PrincipalDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;
    private final AnalyticsService analyticsService;

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

    @GetMapping("/week")
    public List<Long> getVisitor() throws GeneralSecurityException, IOException {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusWeeks(1);
        String startDate = lastWeek.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String endDate = today.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        return analyticsService.getVisitorCount(startDate, endDate);
    }
}
