package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class AWSController {

    @Autowired
    private AWSService awsService;

    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "file")MultipartFile file,
            @RequestBody String fileName) {

        return new ResponseEntity<>(awsService.uploadImg(fileName, file), HttpStatus.OK);
    }

    @GetMapping("/downloadImg/{imgName}")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable String imgName) {

        byte[] data = awsService.downloadImg(imgName);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition",
                        "attachment; filename=\"" + imgName + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteImg/{imgName}")
    public ResponseEntity<String> deleteFile(
            @PathVariable String imgName) {

        return new ResponseEntity<>(awsService.deleteFile(imgName), HttpStatus.OK);
    }
}
