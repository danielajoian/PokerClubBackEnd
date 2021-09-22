package com.codecool.pokerclubbackend.controller;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.ClubRepository;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import com.codecool.pokerclubbackend.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4000")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenServices jwtTokenServices;

    @Autowired
    public AuthenticationController() {
    }

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenServices jwtTokenServices,
                                    PlayerRepository player) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenServices jwtTokenServices,
                                    ClubRepository club) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @PostMapping("/authenticatePlayer")
    public ResponseEntity signinPlayer(@RequestBody PlayerJpa data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,
                            data.getPassword()));
//            List<String> roles = authentication.getAuthorities()
//                    .stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
//            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

//    @PostMapping("/authenticateClub")
//    public ResponseEntity signin(@RequestBody ClubCredentials data) {
//        try {
//            String clubUsername = data.getClubUsername();
//            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
//            Authentication authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(clubUsername,
//                            data.getPassword()));
//            List<String> roles = authentication.getAuthorities()
//                    .stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList());
//
//            String token = jwtTokenServices.createToken(clubUsername, roles);
//
//            Map<Object, Object> model = new HashMap<>();
//            model.put("clubUsername", clubUsername);
//            model.put("roles", roles);
//            model.put("token", token);
//            return ResponseEntity.ok(model);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username/password supplied");
//        }
//    }
}


