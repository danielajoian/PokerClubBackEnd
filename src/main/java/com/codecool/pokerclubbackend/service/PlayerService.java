package com.codecool.pokerclubbackend.service;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private AWSService awsService;
    private PlayerJpa player;

    private PlayerService() {}

    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         AWSService awsService) {
        this.playerRepository = playerRepository;
        this.awsService = awsService;
    }

    public byte[] downloadPlayerImage(Long playerId) {
        player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalStateException("Didn't find player with id: " + playerId));

        if (player.getImageLink() != null) {
            return awsService.downloadImg(player.getImageLink());
        }

        return null;
    }

    public void uploadPlayerImage(Long playerId, MultipartFile file) {

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
        player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalStateException("Didn't find player with id: " + playerId));

        // Store the image
        String fileName = String.format("%s_%s",
                player.getUsername(),
                file.getOriginalFilename());

        awsService.uploadImg(fileName, file);

        //Update db with s3 imageLink
        playerRepository.setPlayerImageLink(playerId, fileName);
    }

    public void deletePlayerImage(Long playerId, String imageName) {
        awsService.deleteFile(imageName);
        playerRepository.deleteById(playerId);
    }
}
