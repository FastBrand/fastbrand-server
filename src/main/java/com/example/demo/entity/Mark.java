package com.example.demo.entity;

import com.example.demo.dto.MarkDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(max=64)
    @NotNull
    private String brand_name;
    @Column
    @Size(max=512)
    @NotNull
    private String description;
    @Column
    @Size(max=256)
    private String image;
    @Column
    @Size(max=256)
    @NotNull
    private String sector;
    @Column
    @Size(max=64)
    @NotNull
    private String type;
    @Column
    @Size(max=16)
    @NotNull
    private String poc;
    @Column
    @Size(max=256)
    private String country;
    @Column
    @Size(max=256)
    private String madrid;
    @Column
    @Size(max=256)
    private String direct;
    @Column
    @Size(max=64)
    private String status;
    @OneToOne(mappedBy = "mark")
    private Corporate corporate;
    @OneToOne(mappedBy = "mark")
    private Personal personal;
    @OneToOne(mappedBy = "mark")
    private User user;

    public static Mark createMark(MarkDto dto) {
        return Mark.builder()
                .id(dto.getId())
                .brand_name(dto.getBrand_name())
                .description(dto.getDescription())
                .image(dto.getImage())
                .sector(dto.getSector())
                .type(dto.getType())
                .poc(dto.getPoc())
                .country(dto.getCountry())
                .madrid(dto.getMadrid())
                .direct(dto.getDirect())
                .status(dto.getStatus())
                .build();
    }

    public void patch(MarkDto mark) {
        if(mark.getBrand_name() != null)
            this.brand_name = mark.getBrand_name();
        if(mark.getDescription() != null)
            this.description = mark.getDescription();
        if(mark.getImage() != null)
            this.image = mark.getImage();
        if(mark.getSector() != null)
            this.sector = mark.getSector();
        if(mark.getType() != null)
            this.type = mark.getType();
        if(mark.getPoc() != null)
            this.poc = mark.getPoc();
        if(mark.getCountry() != null)
            this.country = mark.getCountry();
        if(mark.getStatus() != null)
            this.status = mark.getStatus();
    }
}
