package com.example.demo.service.seal;

import com.example.demo.dto.SealDto;
import com.example.demo.entity.Corporate;
import com.example.demo.entity.Seal;
import com.example.demo.repository.SealRepository;
import com.example.demo.service.corporate.CorporateServiceImpl;
import com.example.demo.service.upload.UploadServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SealServiceImpl implements SealService {
    private final SealRepository sealRepository;
    private final UploadServiceImpl uploadService;
    private final CorporateServiceImpl corporateService;

    @Override
    public List<SealDto> seals() {
        return sealRepository.findAll().stream()
                .map(SealDto::createSealDto)
                .collect(Collectors.toList());
    }

    @Override
    public SealDto getSeal(Long id) {
        Seal seal = sealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        return SealDto.createSealDto(seal);
    }

    @Override
    public List<SealDto> getSealsByCorpId(Long corp_id) {
        Corporate corporate = corporateService._oneCorporate(corp_id);
        List<Seal> seals = corporate.getSeals();
        List<SealDto> list = new ArrayList<>();
        for(Seal seal : seals) {
            list.add(SealDto.createSealDto(seal));
        }
        return list;
    }

    @Override
    public List<SealDto> uploadSeal(Long corp_id, MultipartFile[] uploadFiles, String fileType) { //throws Exception {
        List<SealDto> resultDtoList = new ArrayList<>();
        Corporate corporate = corporateService._oneCorporate(corp_id);

        if (uploadFiles == null) {
            return resultDtoList;
        }

        for (MultipartFile uploadFile: uploadFiles) {
            if (!uploadFile.isEmpty()) {
                String fileSize = String.valueOf(uploadFile.getSize());
                String originalName = StringUtils.cleanPath(Objects.requireNonNull(uploadFile.getOriginalFilename()));
                String uuid = UUID.randomUUID().toString();
                String fileExtension = originalName.substring(originalName.lastIndexOf("."));
                String fileName = uuid + "_" + originalName.replace(fileExtension, "") + fileExtension;

                try {
                    String filePath = uploadService.upload(corp_id, uploadFile, fileType);

                    SealDto sealDto = SealDto.builder()
                            .originalName(originalName)
                            .storedName(fileName)
                            .uuid(uuid)
                            .url(filePath)
                            .fileSize(fileSize)
                            .corp_id(corp_id)
                            .build();

                    Seal seal = Seal.createSeal(sealDto, corporate);
                    Seal created = sealRepository.save(seal);
                    resultDtoList.add(SealDto.createSealDto(created));

                } catch (IOException e) {
                    return resultDtoList;
                }
            }
        }
        return resultDtoList;
    }

    @Override
    public void deleteSeal(List<Seal> seals) {
        for(Seal seal : seals) {
            String key = "seals/" + seal.getOriginalName();
            uploadService.deleteFile(key);
            sealRepository.delete(seal);
        }
    }
}
