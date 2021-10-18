package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import com.codecool.pokerclubbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4000")
@CrossOrigin("*")
public class PlayerController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private PlayerRepository playerRepository;
    private PlayerService playerService;

    private PlayerController() {};

    @Autowired
    public PlayerController(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @GetMapping(path = "/playersByGame/{privateGameId}")
    public List<PlayerJpa> getAllPlayersByGame(@PathVariable Long privateGameId) {
        return playerRepository.findByPrivateGameId(privateGameId);
    }

    @GetMapping(path = "/players/{username}")
    public PlayerJpa getPlayerDetails(@PathVariable String username)
//                                      @PathVariable String password)
    {
        PlayerJpa player = playerRepository.findByUsername(username).get();
            return player;
    }

    //POST -> Create a new player
    @PostMapping(path = "/players/{username}")
    public ResponseEntity<Void> createPlayer(
            @PathVariable String username,
            @RequestBody PlayerJpa player) {

        player.setUsername(username);
        player.setPassword(encoder.encode(player.getPassword()));
        PlayerJpa createdPlayer = playerRepository.save(player);

        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdPlayer.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "/{id}/playerImage/upload",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadPlayerImage(@PathVariable Long id,
                                  @RequestParam MultipartFile file) {
        playerService.uploadPlayerImage(id, file);
    }

    @GetMapping("/{id}/playerImage/download/{imgName}")
    public ResponseEntity<ByteArrayResource> downloadPlayerImage(
            @PathVariable Long id,
            @PathVariable String imgName) {

        byte[] data = playerService.downloadPlayerImage(id);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition",
                        "attachment; filename=\"" + imgName + "\"")
                .body(resource);
    }

    @PutMapping(path = "/playersAddGame/{privateGameId}/{username}")
    public ResponseEntity<PlayerJpa> addPrivateGame(
            @PathVariable Long privateGameId,
            @PathVariable String username,
            @RequestBody PlayerJpa player
    ) {

        player.setPrivateGameId(privateGameId);
        player.setUsername(username);
        PlayerJpa privateGameAdded = playerRepository.save(player);

        return new ResponseEntity<PlayerJpa>(privateGameAdded, HttpStatus.OK);
    }


    //PUT -> Edit/Update a player
    @PutMapping(path = "/players/{username}/{id}")
    public ResponseEntity<PlayerJpa> updatePlayer(
            @PathVariable String username,
            @PathVariable Long id,
            @RequestBody PlayerJpa player) {

        player.setId(id);
        player.setUsername(username);
        player.setCity(player.getCity());
//        player.setPassword(encoder.encode(player.getPassword()));
        PlayerJpa playerUpdated = playerRepository.save(player);

        return new ResponseEntity<PlayerJpa>(playerUpdated, HttpStatus.OK);
    }

    //DELETE -> player
    @DeleteMapping(path = "/players/{username}/{id}/{imageLink}")
    public ResponseEntity<Void> deletePlayer(
            @PathVariable String username,
            @PathVariable String imageLink,
            @PathVariable Long id) {

        playerService.deletePlayerImage(id, imageLink);
//        playerRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
