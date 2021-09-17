package com.codecool.pokerclubbackend.security;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import com.codecool.pokerclubbackend.repository.PlayerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/*
 There must be a class that implements UserDetailsService otherwise Spring will create a default
 UserDetailsService (an instance of InMemoryUserDetailsManager, see UserDetailsServiceAutoConfiguration) that does not
 know where to find users for authentication.
*/
@Component
public class CustomPlayerDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    public CustomPlayerDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User oobject
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlayerJpa player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(player.getUsername(), player.getPassword(),
                player.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
