package com.example.demo.service.image;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Image;
import com.example.demo.entity.Mark;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.MarkRepository;
import com.example.demo.service.upload.UploadServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final MarkRepository markRepository;
    private final UploadServiceImpl uploadService;
    private String uploadDir = System.getProperty("user.dir") + "/Image";

    @Override
    public List<ImageDto> images() {
        return imageRepository.findAll().stream()
                .map(ImageDto::createImageDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto getImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("error"));
        return ImageDto.createImageDto(image);
    }

    @Override
    public List<ImageDto> getImagesByMarkId(Long mark_id) {
        Mark mark = markRepository.findById(mark_id)
                .orElseThrow(IllegalArgumentException::new);
        List<Image> images = mark.getImages();
        List<ImageDto> list = new ArrayList<>();
        for(Image image : images) {
            list.add(ImageDto.createImageDto(image));
        }
        return list;
    }

    @Override
    public List<ImageDto> uploadImage(Long mark_id, MultipartFile[] uploadFiles, String fileType) { //throws Exception {
        List<ImageDto> resultDtoList = new ArrayList<>();
        Mark mark = markRepository.findById(mark_id)
                .orElseThrow(IllegalArgumentException::new);

        if (uploadFiles == null) {
            return resultDtoList;
        }

        for (MultipartFile uploadFile: uploadFiles) {
            if (!uploadFile.isEmpty()) {
                String fileSize = String.valueOf(uploadFile.getSize());
                String originalName = uploadFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String fileExtension = originalName.substring(originalName.lastIndexOf("."));
                String fileName = uuid + "_" + originalName.replace(fileExtension, "") + fileExtension;
            /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDate = simpleDateFormat.format(new Date());*/

                //String filePath = uploadDir + "/" + fileName;

                try {
                    String filePath = uploadService.upload(mark_id, uploadFile, fileType);

                    ImageDto imageDto = ImageDto.builder()
                            .originalName(originalName)
                            .storedName(fileName)
                            .uuid(uuid)
                            .url(filePath)
                            .fileSize(fileSize)
                            .mark_id(mark_id)
                            .build();

                    Image image = Image.createImage(imageDto, mark);
                    Image created = imageRepository.save(image);
                    resultDtoList.add(ImageDto.createImageDto(created));

                } catch (IOException e) {
                    return resultDtoList;
                }
            }
        }
        return resultDtoList;
    }

    @Override
    public void deleteImage(List<Image> images) {
        for(Image image : images) {
            String key = "marks/" + image.getOriginalName();
            uploadService.deleteFile(key);
            imageRepository.delete(image);
        }
    }

    private void storeFile(MultipartFile file, String filePath) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path storedFilePath = uploadPath.resolve(filePath);
        file.transferTo(storedFilePath.toFile());
    }

}
