package com.example.demo.controller;

import com.example.demo.dto.InfoDto;
import com.example.demo.dto.MarkDto;
import com.example.demo.service.File.FileServiceImpl;
import com.example.demo.service.corporate.CorporateServiceImpl;
import com.example.demo.service.info.InfoServiceImpl;
import com.example.demo.service.mark.MarkServiceImpl;
import com.example.demo.service.personal.PersonalServiceImpl;
import com.example.demo.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/edit")
@RequiredArgsConstructor
@RestController
public class EditController {
    private final InfoServiceImpl infoService;
    private final MarkServiceImpl markService;
    private final FileServiceImpl fileService;

    /*
    private final CorporateServiceImpl corporateService;
    private final PersonalServiceImpl personalService;
    private final UserServiceImpl userService;*/

    @PatchMapping("/mark/{id}")
    public ResponseEntity<MarkDto> updateMark(@PathVariable Long id, @RequestBody MarkDto dto) {
        MarkDto markDto = markService.update(id, dto);
        if(markDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(markDto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/info")
    public ResponseEntity<InfoDto> update(@RequestBody InfoDto dto) {
        InfoDto infoDto = infoService.update(dto);
        if(infoDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(infoDto);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/mark/{id}")
    public ResponseEntity<MarkDto> delete(@PathVariable Long id) {
        MarkDto markDto = markService.delete(id);
        if(markDto != null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/upload/{id}")
    public ResponseEntity<String> handleFileUpload(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.uploadFile(id, file));
    }
}
