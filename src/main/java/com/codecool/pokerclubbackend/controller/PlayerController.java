package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4000")
@CrossOrigin("*")
public class PlayerController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private PlayerRepository playerRepository;

    private PlayerController() {};

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
    @DeleteMapping(path = "/players/{username}/{id}")
    public ResponseEntity<Void> deletePlayer(
            @PathVariable String username,
            @PathVariable Long id) {

        playerRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
