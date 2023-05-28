package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.info.InfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegisterController {
    private final InfoServiceImpl infoService;

    @PostMapping("/personal")
    public ResponseEntity<InfoDto> createAllPersonal(@RequestPart("data") InfoDto dto, @RequestPart("image") MultipartFile[] images) {
        InfoDto infoDto = infoService.createPer(dto, images, "image");
        return ResponseEntity.status(HttpStatus.OK).body(infoDto);
    }

    @PostMapping("/corporate")
    public ResponseEntity<InfoDto> createAllCorporate(@RequestPart("data") InfoDto dto, @RequestPart("image") MultipartFile[] images, @RequestPart("seal") MultipartFile[] seals) {
        InfoDto infoDto = infoService.createCorp(dto, images, "image", seals, "seal");;
        return ResponseEntity.status(HttpStatus.OK).body(infoDto);
    }
}
