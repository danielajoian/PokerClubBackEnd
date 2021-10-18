package com.codecool.pokerclubbackend.service;

import com.codecool.pokerclubbackend.model.ClubJpa;
import com.codecool.pokerclubbackend.repository.ClubRepository;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Service
public class ClubService {

    private ClubRepository clubRepository;
    private AWSService awsService;
    private ClubJpa club;

    private ClubService() {}

    @Autowired
    public ClubService(ClubRepository clubRepository,
                         AWSService awsService) {
        this.clubRepository = clubRepository;
        this.awsService = awsService;
    }

    public byte[] downloadClubImage(Long clubId) {
        club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalStateException("Didn't find player with id: " + clubId));

        if (club.getImageLink() != null) {
            return awsService.downloadImg(club.getImageLink());
        }

        return null;
    }

    public void uploadClubImage(Long clubId, MultipartFile file) {

        //Check if image is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        //If file is not an image
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
                        ContentType.IMAGE_PNG.getMimeType())
                .contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }

        //Check if the player is in the database
        club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalStateException("Didn't find player with id: " + clubId));

        // Store the image
        String fileName = String.format("%s_%s",
                club.getClubUsername(),
                file.getOriginalFilename());

        awsService.uploadImg(fileName, file);

        //Update db with s3 imageLink
        clubRepository.setClubImageLink(clubId, fileName);
    }

    public void deleteClubImage(Long clubId, String imageName) {
        awsService.deleteFile(imageName);
        clubRepository.deleteById(clubId);
    }
}
