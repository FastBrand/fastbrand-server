package com.example.demo.dto;

import com.example.demo.entity.Image;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImageDto {
    private Long id;
    private String originalName;
    private String storedName;
    private String url;
    private String uuid;
    private String fileSize;
    private Long mark_id;

    public static ImageDto createImageDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .originalName(image.getOriginalName())
                .storedName(image.getStoredName())
                .url(image.getUrl())
                .uuid(image.getUuid())
                .fileSize(image.getFileSize())
                .mark_id(image.getMark().getId())
                .build();
    }

//    public String imageURL() {
//        try {
//            return URLEncoder.encode(url+"/"+uuid+"_"+originalName,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
}
