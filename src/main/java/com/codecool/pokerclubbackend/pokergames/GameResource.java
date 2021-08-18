package com.codecool.pokerclubbackend.pokergames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class GameResource {

    @Autowired
    private GameHardcodedService gameService;

    @GetMapping(path = "/{clubUsername}/games")
    public List<Game> getAllGames(@PathVariable String clubUsername) {
        return gameService.findAll();
    }

    @GetMapping(path = "/{clubUsername}/games/{id}")
    public Game getGame(
            @PathVariable String clubUsername,
            @PathVariable long id) {
        return gameService.findById(id);
    }

    //DELETE /{clubUsername}/games/{id}
    @DeleteMapping(path = "/{clubUsername}/games/{id}")
    public ResponseEntity<Void> deleteGame(
            @PathVariable String clubUsername,
            @PathVariable long id) {
        Game game = gameService.deleteById(id);
        if (game != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //PUT -> Edit/Update a game
    @PutMapping(path = "/{clubUsername}/games/{id}")
    public ResponseEntity<Game> updateGame(
            @PathVariable String clubUsername,
            @PathVariable long id,
            @RequestBody Game game) {

        Game gameUpdated = gameService.save(game);

        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    //POST -> Create a new game
    @PostMapping(path = "/{clubUsername}/games")
    public ResponseEntity<Void> createGame(
            @PathVariable String clubUsername,
            @RequestBody Game game) {

        Game createdGame = gameService.save(game);

        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdGame.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
