package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.GameJpa;
import com.codecool.pokerclubbackend.repository.GameRepository;
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
public class GameController {

    private GameRepository gameRepository;

    private GameController() {};

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(path = "/jpa/{clubUsername}/games")
    public List<GameJpa> getAllGames(@PathVariable String clubUsername) {
        return gameRepository.findByClubUsername(clubUsername);
    }

    @GetMapping(path = "/jpa/{clubUsername}/games/{id}")
    public GameJpa getGame(
            @PathVariable String clubUsername,
            @PathVariable Long id) {
        return gameRepository.findById(id).get();
    }

    //POST -> Create a new game
    @PostMapping(path = "/newGame/{clubUsername}/games")
    public ResponseEntity<Void> createGame(
            @PathVariable String clubUsername,
            @RequestBody GameJpa game) {

        game.setClubUsername(clubUsername);
        GameJpa createdGame = gameRepository.save(game);

        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdGame.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //PUT -> Edit/Update a game
    @PutMapping(path = "/changeGame/{clubUsername}/games/{id}")
    public ResponseEntity<GameJpa> updateGame(
            @PathVariable String clubUsername,
            @PathVariable Long id,
            @RequestBody GameJpa game) {

        game.setId(id);
        game.setClubUsername(clubUsername);
        GameJpa gameUpdated = gameRepository.save(game);

        return new ResponseEntity<GameJpa>(gameUpdated, HttpStatus.OK);
    }

    //DELETE -> game
    @DeleteMapping(path = "/deleteGame/{clubUsername}/games/{id}")
    public ResponseEntity<Void> deleteGame(
            @PathVariable String clubUsername,
            @PathVariable Long id) {

        gameRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
