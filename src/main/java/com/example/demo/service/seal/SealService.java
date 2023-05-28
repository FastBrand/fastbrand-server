package com.example.demo.service.seal;

import com.example.demo.dto.SealDto;
import com.example.demo.entity.Seal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SealService {
    List<SealDto> seals();
    SealDto getSeal(Long id);
    List<SealDto> uploadSeal(Long corp_id, MultipartFile[] uploadFiles, String fileType);
    List<SealDto> getSealsByCorpId(Long corp_id);
    void deleteSeal(List<Seal> seals);
}
