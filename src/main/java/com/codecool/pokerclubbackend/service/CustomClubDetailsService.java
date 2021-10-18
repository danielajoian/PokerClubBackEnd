package com.codecool.pokerclubbackend.service;

import com.codecool.pokerclubbackend.model.ClubJpa;
import com.codecool.pokerclubbackend.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomClubDetailsService implements UserDetailsService {

    private final ClubRepository clubRepository;

    @Autowired
    public CustomClubDetailsService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User oobject
     */
    @Override
    public UserDetails loadUserByUsername(String clubUsername) throws UsernameNotFoundException {
        ClubJpa clubJpa = clubRepository.findByClubUsername(clubUsername)
                .orElseThrow(() -> new UsernameNotFoundException("ClubUsername: " + clubUsername + " not found"));

        return new User(clubJpa.getClubUsername(), clubJpa.getPassword(),
                clubJpa.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
