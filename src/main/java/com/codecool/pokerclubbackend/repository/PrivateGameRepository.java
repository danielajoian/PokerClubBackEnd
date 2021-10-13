package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.PrivateGameJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivateGameRepository extends JpaRepository<PrivateGameJpa, Long> {

    List<PrivateGameJpa> findByClubUsername(String clubUsername);

    Optional<PrivateGameJpa> findBySecretCode(String secretCode);
}
