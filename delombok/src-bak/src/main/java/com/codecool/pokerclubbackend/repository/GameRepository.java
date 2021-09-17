package com.codecool.pokerclubbackend.repository;

import com.codecool.pokerclubbackend.model.GameJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameJpa, Long> {

    List<GameJpa> findByClubUsername(String clubUsername);
}
