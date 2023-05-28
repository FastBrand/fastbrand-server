package com.example.demo.service.info;

import com.example.demo.dto.*;
import com.example.demo.entity.Corporate;
import com.example.demo.entity.Mark;
import com.example.demo.service.corporate.CorporateServiceImpl;
import com.example.demo.service.image.ImageServiceImpl;
import com.example.demo.service.mark.MarkServiceImpl;
import com.example.demo.service.personal.PersonalServiceImpl;
import com.example.demo.service.seal.SealService;
import com.example.demo.service.seal.SealServiceImpl;
import com.example.demo.service.user.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService{
    private final MarkServiceImpl markService;
    private final PersonalServiceImpl personalService;
    private final CorporateServiceImpl corporateService;
    private final UserServiceImpl userService;
    private final ImageServiceImpl imageService;
    private final SealServiceImpl sealService;

    @Override
    public List<InfoDto> info() {
        List<UserDto> userDtoList = userService.user();
        List<InfoDto> infoDtoList = new ArrayList<>();

        for (UserDto userDto : userDtoList) {
            Mark mark = markService._mark(userDto.getMark_id());
            CorporateDto corporate;
            PersonalDto personal;
            List<ImageDto> images;
            List<SealDto> seals;

            if(mark.getPoc().equals("corporate")) {
                corporate = corporateService.oneCorporate(mark.getCorporate().getId());
                images = imageService.getImagesByMarkId(mark.getId());
                seals = sealService.getSealsByCorpId(corporate.getId());

                InfoDto infoDto = InfoDto.builder()
                        .mark(MarkDto.createMarkDto(mark))
                        .corporate(corporate)
                        .user(userDto)
                        .images(images)
                        .seals(seals)
                        .build();

                infoDtoList.add(infoDto);
            }

            else if(mark.getPoc().equals("personal")) {
                personal = personalService.onePersonal(mark.getPersonal().getId());
                images = imageService.getImagesByMarkId(mark.getId());

                InfoDto infoDto = InfoDto.builder()
                        .mark(MarkDto.createMarkDto(mark))
                        .personal(personal)
                        .user(userDto)
                        .images(images)
                        .build();

                infoDtoList.add(infoDto);
            }
        }

        return infoDtoList;
    }

    @Override
    public InfoDto _info(Long id) {
        Mark mark = markService._mark(id);
        CorporateDto corporateDto;
        PersonalDto personalDto;
        List<ImageDto> images;
        List<SealDto> seals;
        InfoDto infoDto;
        MarkDto markDto = MarkDto.createMarkDto(mark);
        UserDto userDto = userService.oneUser(mark.getUser().getId());

        if(mark.getPoc().equals("corporate")) {
            corporateDto = corporateService.oneCorporate(mark.getCorporate().getId());
            images = imageService.getImagesByMarkId(id);
            seals = sealService.getSealsByCorpId(corporateDto.getId());

            infoDto = InfoDto.builder()
                    .mark(markDto)
                    .corporate(corporateDto)
                    .user(userDto)
                    .images(images)
                    .seals(seals)
                    .build();
        }
        else {
            personalDto = personalService.onePersonal(mark.getPersonal().getId());
            images = imageService.getImagesByMarkId(id);

            infoDto = InfoDto.builder()
                    .mark(markDto)
                    .personal(personalDto)
                    .user(userDto)
                    .images(images)
                    .build();
        }
        return infoDto;
    }

    @Transactional
    @Override
    public InfoDto createCorp(InfoDto dto, MultipartFile[] images, String imageType, MultipartFile[] seals, String sealType) {
        MarkDto mark = markService.create(dto.getMark());
        CorporateDto corporate = corporateService.create(mark.getId(), dto.getCorporate());
        UserDto user = userService.create(mark.getId(), dto.getUser());
        List<ImageDto> imageList = imageService.uploadImage(mark.getId(), images, imageType);
        List<SealDto> sealList = sealService.uploadSeal(corporate.getId(), seals, sealType);

        InfoDto registerDto = InfoDto.builder()
                .mark(mark)
                .corporate(corporate)
                .user(user)
                .images(imageList)
                .seals(sealList)
                .build();

        return registerDto;
    }

    @Transactional
    @Override
    public InfoDto createPer(InfoDto dto, MultipartFile[] images, String imageType) {
        MarkDto mark = markService.create(dto.getMark());
        PersonalDto personal = personalService.create(mark.getId(), dto.getPersonal());
        UserDto user = userService.create(mark.getId(), dto.getUser());
        List<ImageDto> imageList = imageService.uploadImage(mark.getId(), images, imageType);

        InfoDto registerDto = InfoDto.builder()
                .mark(mark)
                .personal(personal)
                .user(user)
                .images(imageList)
                .build();

        return registerDto;
    }

    @Transactional
    @Override
    public InfoDto update(InfoDto dto) {
        MarkDto markDto = dto.getMark();
        markDto = markService.update(markDto.getId(), markDto);

        UserDto userDto = dto.getUser();
        userDto = userService.update(userDto.getId(), userDto);

        if(dto.getMark().getPoc().equals("corporate")) {
            CorporateDto corporateDto = dto.getCorporate();
            corporateDto = corporateService.update(corporateDto.getId(), corporateDto);
            List<ImageDto> images = imageService.getImagesByMarkId(markDto.getId());
            List<SealDto> seals = sealService.getSealsByCorpId(corporateDto.getId());

            InfoDto updateDto = InfoDto.builder()
                    .mark(markDto)
                    .corporate(corporateDto)
                    .user(userDto)
                    .images(images)
                    .seals(seals)
                    .build();

            return updateDto;
        }

        else if(dto.getMark().getPoc().equals("personal")) {
            PersonalDto personalDto = dto.getPersonal();
            personalDto = personalService.update(personalDto.getId(), personalDto);
            List<ImageDto> images = imageService.getImagesByMarkId(markDto.getId());

            InfoDto updateDto = InfoDto.builder()
                    .mark(markDto)
                    .personal(personalDto)
                    .user(userDto)
                    .images(images)
                    .build();

            return updateDto;
        }

        return null;
    }
}
