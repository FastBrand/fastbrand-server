package com.example.demo.service.image;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<ImageDto> images();
    ImageDto getImage(Long id);
    List<ImageDto> uploadImage(Long mark_id, MultipartFile[] uploadFiles, String fileType);
    List<ImageDto> getImagesByMarkId(Long mark_id);
    void deleteImage(List<Image> images);
}
