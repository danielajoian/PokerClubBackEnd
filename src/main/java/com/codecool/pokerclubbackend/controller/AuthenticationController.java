package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.ClubCredentials;
import com.codecool.pokerclubbackend.model.ClubJpa;
import com.codecool.pokerclubbackend.model.PlayerCredentials;
import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.ClubRepository;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import com.codecool.pokerclubbackend.security.JwtTokenServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4000")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenServices jwtTokenServices;

    @Autowired
    public AuthenticationController() {
    }

//    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenServices jwtTokenServices,
                                    PlayerRepository player) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

//    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenServices jwtTokenServices,
                                    ClubRepository club) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    //swagger annotations
    @ApiOperation(value = "Player sign-in",
            notes = "Players that already have an account can sign-in here",
            response = PlayerJpa.class)

    @PostMapping("/authenticatePlayer")
    public ResponseEntity<?> signinPlayer(@RequestBody PlayerJpa data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,
                            data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    //swagger annotations
    @ApiOperation(value = "Club sign-in",
            notes = "Clubs that already have an account can sign-in here",
            response = ClubJpa.class)

    @PostMapping("/authenticateClub")
    public ResponseEntity<?> signin(@RequestBody ClubJpa data) {
        try {
            String clubUsername = data.getClubUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(clubUsername,
                            data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(clubUsername, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("clubUsername", clubUsername);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}


