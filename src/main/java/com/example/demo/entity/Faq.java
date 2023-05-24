package com.example.demo.entity;

import com.example.demo.dto.FaqDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(max=128)
    @NotNull
    private String title;
    @Column
    @Size(max=1024)
    @NotNull
    private String content;

    public void patch(FaqDto faq) {
        if(faq.getTitle() != null)
            this.title = faq.getTitle();
        if(faq.getContent() != null)
            this.content = faq.getContent();
    }

    public static Faq createFaq(FaqDto dto) {
        return Faq.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
