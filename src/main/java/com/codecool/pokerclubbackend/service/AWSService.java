package com.codecool.pokerclubbackend.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class AWSService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3;

    @Autowired
    public AWSService(AmazonS3 s3) {
        this.s3 = s3;
    }

    public String uploadImg(String fileName, MultipartFile img) {
        File convertedFile = convertMultiPartFile(img);
        s3.putObject(new PutObjectRequest(bucketName, fileName, convertedFile));
        convertedFile.delete();
        return "File uploaded: " + fileName;
    }

    public byte[] downloadImg(String imgName) {
        try {
            S3Object s3Object = s3.getObject(bucketName, imgName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        } catch (AmazonServiceException | IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String imgName) {
        s3.deleteObject(bucketName, imgName);
        return imgName + " removed...";
    }

    private File convertMultiPartFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException exception) {
            log.error("Error converting multipartFile to file", exception);
        }
        return convertedFile;
    }
}
