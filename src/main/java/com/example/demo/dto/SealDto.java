package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.example.demo.entity.Seal;
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
public class SealDto {
    private Long id;
    private String originalName;
    private String storedName;
    private String url;
    private String uuid;
    private String fileSize;
    private Long corp_id;

    public static SealDto createSealDto(Seal seal) {
        return SealDto.builder()
                .id(seal.getId())
                .originalName(seal.getOriginalName())
                .storedName(seal.getStoredName())
                .url(seal.getUrl())
                .uuid(seal.getUuid())
                .fileSize(seal.getFileSize())
                .corp_id(seal.getCorporate().getId())
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
