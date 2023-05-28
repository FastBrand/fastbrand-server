package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.corporate.CorporateServiceImpl;
import com.example.demo.service.info.InfoServiceImpl;
import com.example.demo.service.mark.MarkServiceImpl;
import com.example.demo.service.personal.PersonalServiceImpl;
import com.example.demo.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {
    private final InfoServiceImpl infoService;
    private final MarkServiceImpl markService;
    private final PersonalServiceImpl personalService;
    private final CorporateServiceImpl corporateService;
    private final UserServiceImpl userService;

    @GetMapping("/info")
    public List<InfoDto> showAllInfo() {
        return infoService.info();
    }
    @GetMapping("/info/{id}")
    public InfoDto showInfo(@PathVariable Long id) {
        return infoService._info(id);
    }
    @GetMapping("/mark")
    public List<MarkDto> showMarks() {
        return markService.marks();
    }
    @GetMapping("/personal")
    public List<PersonalDto> showPersonals() {
        return personalService.personal();
    }
    @GetMapping("/corporate")
    public List<CorporateDto> showCorporates() {
        return corporateService.corporate();
    }
    @GetMapping("/user")
    public List<UserDto> showUsers() {
        return userService.user();
    }
}
