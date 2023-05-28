package com.example.demo.entity;

import com.example.demo.dto.SealDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Seal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String originalName;
    @Column
    @NotNull
    private String storedName;
    @Column
    @NotNull
    private String url;
    @Column
    @NotNull
    private String uuid;
    @Column
    private String fileSize;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corporate corporate;

    public static Seal createSeal(SealDto sealDto, Corporate corporate) {
        return Seal.builder()
                .id(sealDto.getId())
                .originalName(sealDto.getOriginalName())
                .storedName(sealDto.getStoredName())
                .url(sealDto.getUrl())
                .uuid(sealDto.getUuid())
                .fileSize(sealDto.getFileSize())
                .corporate(corporate)
                .build();
    }
}
