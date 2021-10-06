package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.PlayerJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerJpa, Long> {

    Optional<PlayerJpa> findByUsername(String username);
//    Optional<PlayerJpa> findById(Long id);

    List<PlayerJpa> findByPrivateGameId(Long privateGameId);
}
