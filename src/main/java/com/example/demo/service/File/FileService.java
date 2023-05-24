package com.example.demo.service.File;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(Long id, MultipartFile file) throws IOException;
}
