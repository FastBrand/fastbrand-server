package com.example.demo.service.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.entity.Mark;
import com.example.demo.repository.MarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;
    private final AmazonS3 amazonS3;
    private final MarkRepository markRepository;
    @Override
    public String uploadFile(Long id, MultipartFile file) throws IOException {

        String originalName = "img/" + file.getOriginalFilename();
        long size = file.getSize();

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(file.getContentType());
        objectMetaData.setContentLength(size);

        amazonS3.putObject(
                new PutObjectRequest(S3Bucket, originalName, file.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String imageUrl = amazonS3.getUrl(S3Bucket, originalName).toString();

        Mark mark = markRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error"));
        mark.setImage(imageUrl);
        markRepository.save(mark);

        return imageUrl;
    }
}
