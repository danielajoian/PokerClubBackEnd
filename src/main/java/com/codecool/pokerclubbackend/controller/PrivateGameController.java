package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.GameJpa;
import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.model.PrivateGameJpa;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import com.codecool.pokerclubbackend.repository.PrivateGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4000")
public class PrivateGameController {

    private PrivateGameRepository privateGameRepository;
    private PlayerRepository playerRepository;

    private PrivateGameController() {};

    @Autowired
    public PrivateGameController(PrivateGameRepository privateGameRepository) {
        this.privateGameRepository = privateGameRepository;
    }

    public PrivateGameController(PrivateGameRepository privateGameRepository,
                                 PlayerRepository playerRepository) {
        this.privateGameRepository = privateGameRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping(path = "/{clubUsername}/getPrivateGames")
    public List<PrivateGameJpa> getAllGames(@PathVariable String clubUsername) {
        return privateGameRepository.findByClubUsername(clubUsername);
    }

    @GetMapping(path = "/{clubUsername}/getPrivateGame/{id}")
    public PrivateGameJpa getGame(
            @PathVariable String clubUsername,
            @PathVariable Long id) {
        return privateGameRepository.findById(id).get();
    }

    @GetMapping(path = "/private/{secretCode}")
    public PrivateGameJpa getGameBySecretCode(
            @PathVariable String secretCode) {
        return privateGameRepository.findBySecretCode(secretCode).get();
    }

    //POST -> Create a new game
    @PostMapping(path = "/{clubUsername}/createPrivateGame")
    public ResponseEntity<Void> createGame(
            @PathVariable String clubUsername,
            @RequestBody PrivateGameJpa game) {

        game.setClubUsername(clubUsername);
        PrivateGameJpa createdGame = privateGameRepository.save(game);

        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdGame.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //PUT -> Edit/Update a game
    @PutMapping(path = "/{clubUsername}/changePrivateGame/{id}")
    public ResponseEntity<PrivateGameJpa> updateGame(
            @PathVariable String clubUsername,
            @PathVariable Long id,
            @RequestBody PrivateGameJpa game) {

        game.setId(id);
        game.setClubUsername(clubUsername);
        PrivateGameJpa gameUpdated = privateGameRepository.save(game);

        return new ResponseEntity<PrivateGameJpa>(gameUpdated, HttpStatus.OK);
    }

    //DELETE -> game
    @DeleteMapping(path = "/{clubUsername}/deletePrivateGame/{id}")
    public ResponseEntity<Void> deleteGame(
            @PathVariable String clubUsername,
            @PathVariable Long id) {

        privateGameRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
