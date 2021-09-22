package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.ClubJpa;
import com.codecool.pokerclubbackend.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class ClubController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private ClubRepository clubRepository;

    private ClubController() {}

    @Autowired
    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @GetMapping(path = "/clubs")
    public List<ClubJpa> getAllClubs() {
        return clubRepository.findAll();
    }

    @GetMapping(path = "/clubs/{clubUsername}")
    public ClubJpa getClub(@PathVariable String clubUsername) {
        return clubRepository.findByClubUsername(clubUsername).get();
    }

    //POST -> Create a new club
    @PostMapping(path = "/clubs/{clubUsername}")
    public ResponseEntity<Void> createClub(
            @PathVariable String clubUsername,
            @RequestBody ClubJpa club) {

        club.setClubUsername(clubUsername);
        club.setPassword(encoder.encode(club.getPassword()));
        ClubJpa createdClub = clubRepository.save(club);

        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdClub.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //PUT -> Edit/Update a club
    @PutMapping(path = "/clubs/{clubUsername}/{id}")
    public ResponseEntity<ClubJpa> updateClub(
            @PathVariable String clubUsername,
            @PathVariable Long id,
            @RequestBody ClubJpa club) {

        club.setId(id);
        club.setClubUsername(clubUsername);
        ClubJpa clubUpdated = clubRepository.save(club);

        return new ResponseEntity<ClubJpa>(clubUpdated, HttpStatus.OK);
    }

    //DELETE -> club
    @DeleteMapping(path = "/clubs/{clubUsername}/{id}")
    public ResponseEntity<Void> deleteClub(
            @PathVariable String clubUsername,
            @PathVariable Long id) {

        clubRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
