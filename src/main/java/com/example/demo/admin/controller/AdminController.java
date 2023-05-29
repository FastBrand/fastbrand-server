package com.example.demo.admin.controller;

import com.example.demo.admin.dto.AdminDto;
import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.service.AdminService;
import com.example.demo.admin.service.AnalyticsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;
    private final AnalyticsService analyticsService;

    @GetMapping
    public List<Admin> managers(){
        return adminService.managers();
    }

    @PostMapping("/join")
    public ResponseEntity<AdminDto> create(@RequestBody AdminDto dto) {
        adminService.createAdmin(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable Long id, @RequestBody AdminDto dto) {
        AdminDto adminDto = adminService.updateAdmin(id, dto);
        if(adminDto != null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<AdminDto> delete(@PathVariable Long id) {
        AdminDto adminDto = adminService.deleteAdmin(id);
        if(adminDto != null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @GetMapping("/admin/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "logout";
//    }

    @GetMapping("/admin/dashboard")
    public List<Long> getVisitor() throws GeneralSecurityException, IOException {
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusWeeks(1);
        List<Long> dates = new ArrayList<>();
        for (LocalDate date = lastWeek; !date.isAfter(today.minusDays(1)); date = date.plusDays(1)) {
            String startDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String endDate = date.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
            List<Long> temp = analyticsService.getVisitorCount(startDate, endDate);
            if(!temp.isEmpty())
                dates.add(temp.get(0));
            else
                dates.add(0L);
        }
        return dates;
    }

}
